package com.fintech.loan.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.loan.application.LoanApplicationService;
import com.fintech.loan.domain.model.Applicant;
import com.fintech.loan.domain.model.CreditHistory;
import com.fintech.loan.domain.model.LoanApplication;
import com.fintech.loan.domain.service.LoanApprovalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanApplicationService loanApplicationService;

    @MockBean
    private LoanApprovalService loanApprovalService;

    private LoanApplication sampleLoanApplication;
    private Applicant sampleApplicant;

    @BeforeEach
    void setUp() {
        CreditHistory creditHistory = new CreditHistory(750, new BigDecimal("5000"), 95);
        sampleApplicant = new Applicant(
            "Juan Pérez",
            LocalDate.of(1990, 5, 15),
            new BigDecimal("5000"),
            creditHistory
        );

        sampleLoanApplication = new LoanApplication();
        sampleLoanApplication.setId(UUID.randomUUID());
        sampleLoanApplication.setApplicant(sampleApplicant);
        sampleLoanApplication.setRequestedAmount(new BigDecimal("10000"));
        sampleLoanApplication.setStatus("PENDING");
    }

    @Test
    void createApplication_shouldReturn201_whenValidRequest() throws Exception {
        when(loanApplicationService.createApplication(any(LoanApplication.class)))
            .thenReturn(sampleLoanApplication);

        mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleLoanApplication)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void getApplication_shouldReturn404_whenNotFound() throws Exception {
        UUID applicationId = UUID.randomUUID();
        when(loanApplicationService.getApplication(applicationId)).thenReturn(null);

        mockMvc.perform(get("/api/loans/{id}", applicationId))
            .andExpect(status().isNotFound());
    }

    @Test
    void getApplication_shouldReturn200_whenExists() throws Exception {
        UUID applicationId = sampleLoanApplication.getId();
        when(loanApplicationService.getApplication(applicationId)).thenReturn(sampleLoanApplication);

        mockMvc.perform(get("/api/loans/{id}", applicationId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(applicationId.toString()))
            .andExpect(jsonPath("$.requestedAmount").value(10000));
    }

    @Test
    void evaluateApplication_shouldReturn200_withApprovalResult() throws Exception {
        UUID applicationId = sampleLoanApplication.getId();
        when(loanApprovalService.evaluateLoanApplication(applicationId)).thenReturn(true);

        mockMvc.perform(post("/api/loans/{id}/evaluate", applicationId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.approved").value(true));
    }

    @Test
    void evaluateApplication_shouldReturn400_whenInvalidId() throws Exception {
        UUID invalidId = UUID.randomUUID();
        when(loanApprovalService.evaluateLoanApplication(invalidId))
            .thenThrow(new IllegalArgumentException("Invalid application ID"));

        mockMvc.perform(post("/api/loans/{id}/evaluate", invalidId))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getMaximumAmount_shouldReturn200_withCalculatedAmount() throws Exception {
        when(loanApprovalService.calculateMaximumLoanAmount(any(Applicant.class)))
            .thenReturn(30000.0);

        mockMvc.perform(post("/api/loans/maximum-amount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleApplicant)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maximumAmount").value(30000.0));
    }

    @Test
    void createApplication_shouldReturn400_whenInvalidRequest() throws Exception {
        LoanApplication invalidApplication = new LoanApplication();
        invalidApplication.setRequestedAmount(new BigDecimal("-1000"));

        mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidApplication)))
            .andExpect(status().isBadRequest());
    }
}