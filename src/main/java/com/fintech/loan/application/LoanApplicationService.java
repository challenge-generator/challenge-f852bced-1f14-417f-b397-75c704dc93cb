package com.fintech.loan.application;

import com.fintech.loan.domain.model.Applicant;
import com.fintech.loan.domain.model.CreditHistory;
import com.fintech.loan.domain.model.LoanApplication;
import com.fintech.loan.domain.service.LoanApprovalService;
import com.fintech.loan.infrastructure.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LoanApplicationService {

    private final LoanApplicationRepository repository;
    private final LoanApprovalService approvalService;

    public LoanApplicationService(LoanApplicationRepository repository, LoanApprovalService approvalService) {
        this.repository = repository;
        this.approvalService = approvalService;
    }

    public LoanApplication createApplication(String fullName, LocalDate dateOfBirth, 
                                              BigDecimal monthlyIncome, Integer creditScore,
                                              BigDecimal totalDebt, Integer paymentHistory,
                                              BigDecimal requestedAmount, Integer termMonths) {
        CreditHistory creditHistory = new CreditHistory(creditScore, totalDebt, paymentHistory);
        Applicant applicant = new Applicant(fullName, dateOfBirth, monthlyIncome, creditHistory);
        
        LoanApplication application = new LoanApplication();
        application.setId(UUID.randomUUID());
        application.setApplicant(applicant);
        application.setRequestedAmount(requestedAmount);
        application.setTermMonths(termMonths);
        application.setStatus("PENDING");
        application.setApplicationDate(LocalDate.now());
        
        return repository.save(application);
    }

    public Optional<LoanApplication> findById(UUID id) {
        return repository.findById(id);
    }

    public List<LoanApplication> findAll() {
        return repository.findAll();
    }

    public List<LoanApplication> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    public boolean evaluateAndApprove(UUID applicationId) {
        Optional<LoanApplication> optApp = repository.findById(applicationId);
        if (optApp.isEmpty()) {
            return false;
        }
        
        LoanApplication application = optApp.get();
        if (!"PENDING".equals(application.getStatus())) {
            return false;
        }
        
        boolean approved = approvalService.evaluateLoanApplication(applicationId);
        application.setStatus(approved ? "APPROVED" : "REJECTED");
        application.setApprovalDate(LocalDate.now());
        repository.save(application);
        
        return approved;
    }

    public double getMaximumLoanAmount(Applicant applicant) {
        return approvalService.calculateMaximumLoanAmount(applicant);
    }

    public LoanApplication updateApplicationStatus(UUID id, String newStatus) {
        Optional<LoanApplication> optApp = repository.findById(id);
        if (optApp.isEmpty()) {
            throw new IllegalArgumentException("Application not found: " + id);
        }
        
        LoanApplication application = optApp.get();
        application.setStatus(newStatus);
        if ("APPROVED".equals(newStatus) || "REJECTED".equals(newStatus)) {
            application.setApprovalDate(LocalDate.now());
        }
        
        return repository.save(application);
    }

    public void deleteApplication(UUID id) {
        repository.deleteById(id);
    }

    public long countByStatus(String status) {
        return repository.countByStatus(status);
    }

    public List<LoanApplication> findByApplicantId(UUID applicantId) {
        return repository.findByApplicantId(applicantId);
    }
}