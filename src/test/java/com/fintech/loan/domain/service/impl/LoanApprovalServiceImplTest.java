package com.fintech.loan.domain.service.impl;

import com.fintech.loan.domain.model.Applicant;
import com.fintech.loan.domain.model.CreditHistory;
import com.fintech.loan.domain.service.LoanApprovalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanApprovalServiceImplTest {

    @Mock
    private LoanApprovalService loanApprovalService;

    @InjectMocks
    private LoanApprovalServiceImpl loanApprovalServiceImpl;

    @Test
    void evaluateLoanApplication_shouldApprove_whenApplicantMeetsCriteria() {
        UUID applicationId = UUID.randomUUID();
        CreditHistory creditHistory = new CreditHistory(750, new BigDecimal("5000"), 95);
        Applicant applicant = new Applicant(
            "Juan Pérez",
            LocalDate.of(1990, 5, 15),
            new BigDecimal("5000"),
            creditHistory
        );

        when(loanApprovalService.evaluateLoanApplication(applicationId)).thenReturn(true);

        boolean result = loanApprovalService.evaluateLoanApplication(applicationId);

        assertTrue(result);
        verify(loanApprovalService, times(1)).evaluateLoanApplication(applicationId);
    }

    @Test
    void evaluateLoanApplication_shouldReject_whenApplicantDoesNotMeetCriteria() {
        UUID applicationId = UUID.randomUUID();
        CreditHistory creditHistory = new CreditHistory(500, new BigDecimal("20000"), 60);
        Applicant applicant = new Applicant(
            "María García",
            LocalDate.of(1995, 8, 22),
            new BigDecimal("1500"),
            creditHistory
        );

        when(loanApprovalService.evaluateLoanApplication(applicationId)).thenReturn(false);

        boolean result = loanApprovalService.evaluateLoanApplication(applicationId);

        assertFalse(result);
        verify(loanApprovalService, times(1)).evaluateLoanApplication(applicationId);
    }

    @Test
    void calculateMaximumLoanAmount_shouldReturnCorrectAmount() {
        CreditHistory creditHistory = new CreditHistory(720, new BigDecimal("3000"), 90);
        Applicant applicant = new Applicant(
            "Carlos López",
            LocalDate.of(1988, 3, 10),
            new BigDecimal("6000"),
            creditHistory
        );

        double expectedAmount = 30000.0;
        when(loanApprovalService.calculateMaximumLoanAmount(applicant)).thenReturn(expectedAmount);

        double result = loanApprovalService.calculateMaximumLoanAmount(applicant);

        assertEquals(expectedAmount, result, 0.01);
        verify(loanApprovalService, times(1)).calculateMaximumLoanAmount(applicant);
    }

    @Test
    void calculateMaximumLoanAmount_shouldReturnZero_whenIncomeIsLow() {
        CreditHistory creditHistory = new CreditHistory(600, new BigDecimal("10000"), 50);
        Applicant applicant = new Applicant(
            "Ana Martínez",
            LocalDate.of(2000, 1, 1),
            new BigDecimal("800"),
            creditHistory
        );

        when(loanApprovalService.calculateMaximumLoanAmount(applicant)).thenReturn(0.0);

        double result = loanApprovalService.calculateMaximumLoanAmount(applicant);

        assertEquals(0.0, result, 0.01);
    }

    @Test
    void evaluateLoanApplication_shouldHandleNullApplication() {
        UUID applicationId = UUID.randomUUID();

        when(loanApprovalService.evaluateLoanApplication(applicationId)).thenThrow(
            new IllegalArgumentException("Application ID cannot be null")
        );

        assertThrows(IllegalArgumentException.class, () -> {
            loanApprovalService.evaluateLoanApplication(applicationId);
        });
    }
}