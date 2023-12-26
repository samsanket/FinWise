package com.exp.FinWise.audit.controller;


import com.exp.FinWise.audit.dto.AuditDtoPy;
import com.exp.FinWise.audit.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {

    @Autowired
    AuditService auditService;

    @PostMapping("/save")
    public ResponseEntity saveAudiLogs(@RequestBody AuditDtoPy auditDtoPy){
        return auditService.saveAuditFromPython(auditDtoPy);
    }
}
