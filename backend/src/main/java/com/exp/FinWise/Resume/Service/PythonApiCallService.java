/*
 * Copyright (c) 2023.
 * Sanket Deshpande . All rights reserved
 */

package com.exp.FinWise.Resume.Service;

import com.exp.FinWise.Resume.dto.ScrapeDataDTO;
import com.exp.FinWise.Resume.model.RequirementModel;
import com.exp.FinWise.Resume.model.ScrapeData;
import com.exp.FinWise.Resume.repository.RequirementRepository;
import com.exp.FinWise.Resume.repository.ScrapeDataRepo;
import com.exp.FinWise.audit.model.AuditLog;
import com.exp.FinWise.audit.service.AuditService;
import com.exp.FinWise.response.ResponseSBuilder;
import com.exp.FinWise.response.ResponseResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PythonApiCallService {

    @Autowired
    RequirementRepository requirementRepository;

    @Autowired
    ScrapeDataRepo scrapeDataRepo;

    @Autowired
    ResponseSBuilder responseSBuilder;

    @Autowired
    AuditService auditService;

    private void saveAudit(AuditLog auditLog){
        auditService.saveAudit(auditLog);
    }
    public ResponseResume scrapeDataSave(ScrapeDataDTO scrapeDataDTO) {
        ScrapeData scrapeData= new ScrapeData();
        scrapeData.setContactDetails(scrapeDataDTO.getContactDetails());
        scrapeData.setDesignation(scrapeDataDTO.getDesignation());
        scrapeData.setEmail(scrapeDataDTO.getEmail());
        scrapeData.setCurrentLocation(scrapeDataDTO.getCurrentLocation());
        scrapeData.setEmployeeName(scrapeDataDTO.getEmployeeName());
        scrapeData.setQualification(scrapeDataDTO.getQualification());
        scrapeData.setEmployeeExperience(scrapeDataDTO.getEmployeeExperience());
        scrapeData.setWorkExperience(scrapeDataDTO.getWorkExperience());
        scrapeData.setNoticePeriod(scrapeDataDTO.getNoticePeriod());
//        scrapeData.setSimilarityScore(scrapeDataDTO.getSimilarityScore());
        scrapeData.setPreferLocations(scrapeDataDTO.getPreferLocations());
        Optional<RequirementModel> optionalRequirementModel=requirementRepository.findById(scrapeDataDTO.getRequirementId());
        RequirementModel requirementModel= new RequirementModel();
        if (optionalRequirementModel.isPresent()){
            requirementModel=optionalRequirementModel.get();
        }
        scrapeData.setRequirement(requirementModel);
        scrapeDataRepo.save(scrapeData);

        AuditLog auditLog= new AuditLog("python","scarep data save method ","data saved success"+ scrapeData.getEmail(), LocalDateTime.now());
        saveAudit(auditLog);

        return responseSBuilder.createResponse(HttpStatus.OK);

    }
}
