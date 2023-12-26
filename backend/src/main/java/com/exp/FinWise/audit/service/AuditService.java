package com.exp.FinWise.audit.service;

import com.exp.FinWise.audit.dto.AuditDtoPy;
import com.exp.FinWise.audit.model.AuditLog;
import com.exp.FinWise.audit.repository.AuditRepo;
import com.exp.FinWise.usersOnBoarding.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {


    @Autowired
    AuditRepo auditRepo;

    public void saveAudit(String apiName, String data, String user){

        AuditLog auditLog= new AuditLog();
        auditLog.setApiName(apiName);
        auditLog.setData(data);
        auditLog.setUser(user);
        auditLog.setDateTime(LocalDateTime.now());
        auditRepo.save(auditLog);


    }

    public void saveAudit(AuditLog auditLog){
        auditRepo.save(auditLog);
    }

    public void saveAudit(String apiName, String data){

        AuditLog auditLog= new AuditLog();
        auditLog.setApiName(apiName);
        auditLog.setData(data);
        auditLog.setUser(null);
        auditLog.setDateTime(LocalDateTime.now());
        auditRepo.save(auditLog);
    }

    public ResponseEntity saveAuditFromPython(AuditDtoPy auditDtoPy) {
        AuditLog auditLog= new AuditLog(auditDtoPy);
        return  new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
