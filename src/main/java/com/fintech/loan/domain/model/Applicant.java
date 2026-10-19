package com.fintech.loan.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Applicant {
    private UUID id;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre completo debe tener entre 2 y 100 caracteres")
    private String fullName;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDate dateOfBirth;

    @NotNull(message = "El ingreso mensual no puede ser nulo")
    @Positive(message = "El ingreso mensual debe ser positivo")
    private BigDecimal monthlyIncome;

    @NotNull(message = "El historial crediticio no puede ser nulo")
    private CreditHistory creditHistory;

    public Applicant() {
        this.id = UUID.randomUUID();
    }

    public Applicant(String fullName, LocalDate dateOfBirth, BigDecimal monthlyIncome, CreditHistory creditHistory) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.monthlyIncome = monthlyIncome;
        this.creditHistory = creditHistory;
    }

    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    public boolean hasStableIncome() {
        return monthlyIncome.compareTo(new BigDecimal("1000")) >= 0;
    }
}