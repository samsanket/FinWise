package com.exp.FinWise.audit.model;

import com.exp.FinWise.audit.dto.AuditDtoPy;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_log_id")
    private Long auditLogId;


    @Column(name = "user_name")
    private String user;

    @Column(name = "api_name", columnDefinition = "TEXT")
    private String apiName;

    @Column(name = "data" , columnDefinition = "TEXT")
    private  String data;


    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public AuditLog() {
    }

    public AuditLog(String user, String apiName, String data, LocalDateTime dateTime) {
        this.user = user;
        this.apiName = apiName;
        this.data = data;
        this.dateTime = dateTime;
    }

    public AuditLog(AuditDtoPy auditDtoPy) {
       this.setData(auditDtoPy.getData());
       this.setDateTime(LocalDateTime.now());
       this.setApiName(auditDtoPy.getApiName());
       this.setUser(auditDtoPy.getUser());
    }

    public Long getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(Long auditLogId) {
        this.auditLogId = auditLogId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}