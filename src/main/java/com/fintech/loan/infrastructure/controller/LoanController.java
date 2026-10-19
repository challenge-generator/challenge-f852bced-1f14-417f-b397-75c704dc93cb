package com.fintech.loan.infrastructure.controller;

import com.fintech.loan.application.LoanApplicationService;
import com.fintech.loan.domain.model.LoanApplication;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/loan-applications")
public class LoanController {

    private final LoanApplicationService service;

    public LoanController(LoanApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoanApplicationResponse> createApplication(
            @Valid @RequestBody CreateLoanApplicationRequest request) {
        LoanApplication application = service.createApplication(
            request.fullName(),
            request.dateOfBirth(),
            request.monthlyIncome(),
            request.creditScore(),
            request.totalDebt(),
            request.paymentHistory(),
            request.requestedAmount(),
            request.termMonths()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(LoanApplicationResponse.from(application));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationResponse> getById(@PathVariable UUID id) {
        return service.findById(id)
            .map(app -> ResponseEntity.ok(LoanApplicationResponse.from(app)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationResponse>> getAll() {
        List<LoanApplicationResponse> responses = service.findAll()
            .stream()
            .map(LoanApplicationResponse::from)
            .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanApplicationResponse>> getByStatus(@PathVariable String status) {
        List<LoanApplicationResponse> responses = service.findByStatus(status)
            .stream()
            .map(LoanApplicationResponse::from)
            .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/{id}/evaluate")
    public ResponseEntity<EvaluationResponse> evaluateApplication(@PathVariable UUID id) {
        boolean approved = service.evaluateAndApprove(id);
        return ResponseEntity.ok(new EvaluationResponse(approved, approved ? "APPROVED" : "REJECTED"));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<LoanApplicationResponse> updateStatus(
            @PathVariable UUID id,
            @RequestBody UpdateStatusRequest request) {
        try {
            LoanApplication updated = service.updateApplicationStatus(id, request.status());
            return ResponseEntity.ok(LoanApplicationResponse.from(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/maximum-amount")
    public ResponseEntity<MaximumAmountResponse> getMaximumAmount(@PathVariable UUID id) {
        return service.findById(id)
            .map(app -> {
                double maxAmount = service.getMaximumLoanAmount(app.getApplicant());
                return ResponseEntity.ok(new MaximumAmountResponse(maxAmount));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable UUID id) {
        service.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<CountResponse> countByStatus(@PathVariable String status) {
        long count = service.countByStatus(status);
        return ResponseEntity.ok(new CountResponse(status, count));
    }

    public record CreateLoanApplicationRequest(
        @NotBlank String fullName,
        @NotNull LocalDate dateOfBirth,
        @NotNull @Positive BigDecimal monthlyIncome,
        @NotNull Integer creditScore,
        @NotNull BigDecimal totalDebt,
        @NotNull Integer paymentHistory,
        @NotNull @Positive BigDecimal requestedAmount,
        @NotNull @Positive Integer termMonths
    ) {}

    public record UpdateStatusRequest(@NotBlank String status) {}

    public record LoanApplicationResponse(
        UUID id,
        String applicantName,
        BigDecimal requestedAmount,
        Integer termMonths,
        String status,
        LocalDate applicationDate,
        LocalDate approvalDate
    ) {
        public static LoanApplicationResponse from(LoanApplication app) {
            return new LoanApplicationResponse(
                app.getId(),
                app.getApplicant().getFullName(),
                app.getRequestedAmount(),
                app.getTermMonths(),
                app.getStatus(),
                app.getApplicationDate(),
                app.getApprovalDate()
            );
        }
    }

    public record EvaluationResponse(boolean approved, String status) {}

    public record MaximumAmountResponse(double maximumAmount) {}

    public record CountResponse(String status, long count) {}
}