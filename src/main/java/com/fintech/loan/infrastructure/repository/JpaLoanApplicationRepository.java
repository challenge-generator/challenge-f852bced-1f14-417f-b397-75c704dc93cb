package com.fintech.loan.infrastructure.repository;

import com.fintech.loan.domain.model.LoanApplication;
import com.fintech.loan.domain.model.Applicant;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class JpaLoanApplicationRepository implements LoanApplicationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        if (loanApplication.getId() == null) {
            loanApplication.setId(UUID.randomUUID());
            entityManager.persist(loanApplication);
            return loanApplication;
        } else {
            return entityManager.merge(loanApplication);
        }
    }

    @Override
    public Optional<LoanApplication> findById(UUID id) {
        LoanApplication result = entityManager.find(LoanApplication.class, id);
        return Optional.ofNullable(result);
    }

    @Override
    public List<LoanApplication> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoanApplication> query = cb.createQuery(LoanApplication.class);
        Root<LoanApplication> root = query.from(LoanApplication.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<LoanApplication> findByStatus(String status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoanApplication> query = cb.createQuery(LoanApplication.class);
        Root<LoanApplication> root = query.from(LoanApplication.class);
        query.select(root).where(cb.equal(root.get("status"), status));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<LoanApplication> findByApplicantId(UUID applicantId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoanApplication> query = cb.createQuery(LoanApplication.class);
        Root<LoanApplication> root = query.from(LoanApplication.class);
        Join<LoanApplication, Applicant> applicantJoin = root.join("applicant", JoinType.INNER);
        query.select(root).where(cb.equal(applicantJoin.get("id"), applicantId));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public long countByStatus(String status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<LoanApplication> root = query.from(LoanApplication.class);
        query.select(cb.count(root)).where(cb.equal(root.get("status"), status));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void deleteById(UUID id) {
        LoanApplication loanApplication = findById(id).orElse(null);
        if (loanApplication != null) {
            entityManager.remove(loanApplication);
        }
    }

    @Override
    public boolean existsById(UUID id) {
        LoanApplication result = entityManager.find(LoanApplication.class, id);
        return result != null;
    }
}