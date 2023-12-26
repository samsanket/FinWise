/*
 * Copyright (c) 2023.
 * Sanket Deshpande . All rights reserved
 */

package com.exp.FinWise.Resume.Controller;

import com.exp.FinWise.Resume.Service.PythonApiCallService;
import com.exp.FinWise.Resume.dto.ScrapeDataDTO;
import com.exp.FinWise.annotation.CurrentUser;
import com.exp.FinWise.audit.model.AuditLog;
import com.exp.FinWise.audit.service.AuditService;
import com.exp.FinWise.response.ResponseSBuilder;
import com.exp.FinWise.response.ResponseResume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController()
@RequestMapping("/api/v1/py/")
public class PythonCallController {
    Logger log = LoggerFactory.getLogger(PythonCallController.class);

    @Autowired
    ResponseSBuilder responseSBuilder;

    @Autowired
    PythonApiCallService pythonApiCallService;

    @Autowired
    AuditService auditService;

    private void saveAudit(AuditLog auditLog){
        auditService.saveAudit(auditLog);
    }
    private void logData(String apiName, String data) {
        log.info("API called {}", apiName);
        log.info("Data: {}", data);
    }

    @PostMapping("scrape/save/")
    public ResponseResume scrapeSave(@RequestBody ScrapeDataDTO scrapeDataDTO){
        String apiname = "/api/v1/py/scrape/save";
        String data = "saving scarpe data to Db for candidate " + scrapeDataDTO.getEmail();
        AuditLog auditLog= new AuditLog("python",apiname,data, LocalDateTime.now());
        saveAudit(auditLog);
        logData(apiname, data);


        return pythonApiCallService.scrapeDataSave(scrapeDataDTO);
    }

}
