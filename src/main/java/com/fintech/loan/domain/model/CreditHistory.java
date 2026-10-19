package com.fintech.loan.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CreditHistory {
    private UUID id;

    @NotNull(message = "El puntaje crediticio no puede ser nulo")
    private Integer creditScore;

    @NotNull(message = "La deuda total no puede ser nula")
    private BigDecimal totalDebt;

    @NotNull(message = "El historial de pagos no puede ser nulo")
    private Integer paymentHistory;

    private LocalDate lastCreditCheckDate;

    public CreditHistory() {
        this.id = UUID.randomUUID();
    }

    public CreditHistory(Integer creditScore, BigDecimal totalDebt, Integer paymentHistory) {
        this.id = UUID.randomUUID();
        this.creditScore = creditScore;
        this.totalDebt = totalDebt;
        this.paymentHistory = paymentHistory;
        this.lastCreditCheckDate = LocalDate.now();
    }

    public boolean hasGoodCreditScore() {
        return creditScore != null && creditScore >= 700;
    }

    public boolean hasLowDebt() {
        return totalDebt != null && totalDebt.compareTo(new BigDecimal("5000")) < 0;
    }

    public boolean hasPositivePaymentHistory() {
        return paymentHistory != null && paymentHistory >= 0;
    }
}