package com.fintech.loan.domain.service;

import com.fintech.loan.domain.model.Applicant;

import java.util.UUID;

public interface LoanApprovalService {
    /**
     * Evalúa una solicitud de préstamo y determina si debe ser aprobada o rechazada.
     * @param loanApplicationId Identificador único de la solicitud de préstamo.
     * @return true si la solicitud es aprobada, false si es rechazada.
     */
    boolean evaluateLoanApplication(UUID loanApplicationId);

    /**
     * Calcula el monto máximo de préstamo que puede ser aprobado para un solicitante.
     * @param applicant Solicitante para el cual se calcula el monto.
     * @return Monto máximo aprobable.
     */
    double calculateMaximumLoanAmount(Applicant applicant);
}