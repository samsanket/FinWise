package com.exp.FinWise.audit.repository;

import com.exp.FinWise.audit.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepo extends JpaRepository<AuditLog, Long> {
}
