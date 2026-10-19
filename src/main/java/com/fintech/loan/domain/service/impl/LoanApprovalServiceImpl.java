package com.fintech.loan.domain.service.impl;

import com.fintech.loan.domain.model.Applicant;
import com.fintech.loan.domain.model.CreditHistory;
import com.fintech.loan.domain.model.LoanApplication;
import com.fintech.loan.domain.service.LoanApprovalService;
import com.fintech.loan.infrastructure.repository.LoanApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanApprovalServiceImpl implements LoanApprovalService {

    private static final Logger logger = LoggerFactory.getLogger(LoanApprovalServiceImpl.class);

    private static final BigDecimal MIN_INCOME_THRESHOLD = new BigDecimal("500.0");
    private static final BigDecimal MAX_DEBT_TO_INCOME_RATIO = new BigDecimal("0.4");
    private static final BigDecimal MIN_CREDIT_SCORE = new BigDecimal("600");
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 70;
    private static final BigDecimal BASE_INTEREST_RATE = new BigDecimal("0.12");
    private static final BigDecimal EXCELLENT_INTEREST_RATE = new BigDecimal("0.08");
    private static final BigDecimal GOOD_INTEREST_RATE = new BigDecimal("0.10");

    private final LoanApplicationRepository repository;

    public LoanApprovalServiceImpl(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean evaluateLoanApplication(UUID loanApplicationId) {
        logger.info("Evaluando solicitud de préstamo con ID: {}", loanApplicationId);

        Optional<LoanApplication> optionalApplication = repository.findById(loanApplicationId);
        if (optionalApplication.isEmpty()) {
            logger.warn("No se encontró la solicitud de préstamo con ID: {}", loanApplicationId);
            return false;
        }

        LoanApplication application = optionalApplication.get();

        if (!application.isPending()) {
            logger.info("La solicitud {} ya fue procesada previamente", loanApplicationId);
            return application.isApproved();
        }

        Applicant applicant = application.getApplicant();

        if (!validateApplicantAge(applicant)) {
            application.reject("El solicitante no cumple con la edad requerida (18-70 años)");
            repository.save(application);
            logger.info("Solicitud {} rechazada: edad fuera del rango permitido", loanApplicationId);
            return false;
        }

        if (!validateIncomeStability(applicant)) {
            application.reject("El solicitante no tiene ingresos estables suficientes");
            repository.save(application);
            logger.info("Solicitud {} rechazada: ingresos insuficientes", loanApplicationId);
            return false;
        }

        if (!validateCreditHistory(applicant.getCreditHistory())) {
            application.reject("El historial crediticio no cumple con los requisitos mínimos");
            repository.save(application);
            logger.info("Solicitud {} rechazada: historial crediticio insuficiente", loanApplicationId);
            return false;
        }

        if (!validateDebtToIncomeRatio(applicant)) {
            application.reject("La relación deuda/ingresos excede el límite permitido");
            repository.save(application);
            logger.info("Solicitud {} rechazada: deuda/ingresos muy alta", loanApplicationId);
            return false;
        }

        BigDecimal maximumAmount = calculateMaximumLoanAmount(applicant);
        BigDecimal requestedAmount = application.getRequestedAmount();

        if (requestedAmount.compareTo(maximumAmount) > 0) {
            application.reject("El monto solicitado excede el límite máximo aprobado: " + maximumAmount);
            repository.save(application);
            logger.info("Solicitud {} rechazada: monto solicitado {} mayor al máximo {}", 
                loanApplicationId, requestedAmount, maximumAmount);
            return false;
        }

        BigDecimal approvedAmount = requestedAmount;
        BigDecimal interestRate = determineInterestRate(applicant.getCreditHistory());

        application.approve(approvedAmount, interestRate);
        repository.save(application);

        logger.info("Solicitud {} aprobada por monto {} con tasa de interés {}%", 
            loanApplicationId, approvedAmount, interestRate.multiply(BigDecimal.valueOf(100)));
        return true;
    }

    @Override
    public double calculateMaximumLoanAmount(Applicant applicant) {
        if (applicant == null || applicant.getMonthlyIncome() == null) {
            return 0.0;
        }

        BigDecimal monthlyIncome = applicant.getMonthlyIncome();
        CreditHistory creditHistory = applicant.getCreditHistory();

        BigDecimal baseMultiplier = new BigDecimal("10");
        BigDecimal creditScoreBonus = BigDecimal.ZERO;

        if (creditHistory != null) {
            if (creditHistory.hasGoodCreditScore()) {
                creditScoreBonus = new BigDecimal("5");
            }
            if (creditHistory.hasGoodCreditScore() && creditHistory.hasLowDebt()) {
                creditScoreBonus = new BigDecimal("8");
            }
            if (creditHistory.hasPositivePaymentHistory()) {
                creditScoreBonus = creditScoreBonus.add(new BigDecimal("2"));
            }
        }

        BigDecimal totalMultiplier = baseMultiplier.add(creditScoreBonus);
        BigDecimal maximumAmount = monthlyIncome.multiply(totalMultiplier);

        return maximumAmount.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private boolean validateApplicantAge(Applicant applicant) {
        int age = applicant.getAge();
        return age >= MIN_AGE && age <= MAX_AGE;
    }

    private boolean validateIncomeStability(Applicant applicant) {
        return applicant.hasStableIncome() && 
               applicant.getMonthlyIncome() != null && 
               applicant.getMonthlyIncome().compareTo(MIN_INCOME_THRESHOLD) >= 0;
    }

    private boolean validateCreditHistory(CreditHistory creditHistory) {
        if (creditHistory == null) {
            return false;
        }

        boolean hasGoodScore = creditHistory.hasGoodCreditScore();
        boolean hasLowDebt = creditHistory.hasLowDebt();
        boolean hasPositiveHistory = creditHistory.hasPositivePaymentHistory();

        int passingCriteria = 0;
        if (hasGoodScore) passingCriteria++;
        if (hasLowDebt) passingCriteria++;
        if (hasPositiveHistory) passingCriteria++;

        return passingCriteria >= 2;
    }

    private boolean validateDebtToIncomeRatio(Applicant applicant) {
        BigDecimal monthlyIncome = applicant.getMonthlyIncome();
        BigDecimal totalDebt = applicant.getCreditHistory() != null ? 
            applicant.getCreditHistory().getTotalDebt() : BigDecimal.ZERO;

        if (monthlyIncome.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        BigDecimal debtToIncome = totalDebt.divide(monthlyIncome, 4, RoundingMode.HALF_UP);
        return debtToIncome.compareTo(MAX_DEBT_TO_INCOME_RATIO) < 0;
    }

    private BigDecimal determineInterestRate(CreditHistory creditHistory) {
        if (creditHistory == null) {
            return BASE_INTEREST_RATE;
        }

        if (creditHistory.hasGoodCreditScore() && 
            creditHistory.getCreditScore() >= 750 && 
            creditHistory.hasPositivePaymentHistory()) {
            return EXCELLENT_INTEREST_RATE;
        }

        if (creditHistory.hasGoodCreditScore() && creditHistory.hasPositivePaymentHistory()) {
            return GOOD_INTEREST_RATE;
        }

        return BASE_INTEREST_RATE;
    }
}