package com.fintech.loan.infrastructure.repository;


import com.fintech.loan.domain.model.LoanStatus;
import com.fintech.loan.domain.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, UUID> {

    List<LoanApplication> findByStatus(LoanApplication.LoanStatus status);

    @Query("SELECT la FROM LoanApplication la WHERE la.applicant.id = :applicantId")
    List<LoanApplication> findByApplicantId(@Param("applicantId") UUID applicantId);

    @Query("SELECT la FROM LoanApplication la WHERE la.createdAt BETWEEN :startDate AND :endDate")
    List<LoanApplication> findByCreatedAtBetween(
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT la FROM LoanApplication la WHERE la.status = :status AND la.requestedAmount >= :minAmount")
    List<LoanApplication> findByStatusAndMinimumAmount(
        @Param("status") LoanApplication.LoanStatus status,
        @Param("minAmount") BigDecimal minAmount
    );

    @Query("SELECT COUNT(la) FROM LoanApplication la WHERE la.status = :status")
    long countByStatus(@Param("status") LoanApplication.LoanStatus status);

    @Query("SELECT la FROM LoanApplication la WHERE la.applicant.id = :applicantId AND la.status = :status")
    List<LoanApplication> findByApplicantIdAndStatus(
        @Param("applicantId") UUID applicantId,
        @Param("status") LoanApplication.LoanStatus status
    );
}