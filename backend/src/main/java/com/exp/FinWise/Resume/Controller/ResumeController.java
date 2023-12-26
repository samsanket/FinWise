package com.exp.FinWise.Resume.Controller;

import com.exp.FinWise.Resume.Service.MailService;
import com.exp.FinWise.Resume.Service.ResumeService;
import com.exp.FinWise.Resume.dto.*;
import com.exp.FinWise.Resume.model.RequirementModel;
import com.exp.FinWise.Resume.model.ResumeData;
import com.exp.FinWise.annotation.CurrentUser;
import com.exp.FinWise.audit.model.AuditLog;
import com.exp.FinWise.audit.service.AuditService;
import com.exp.FinWise.response.ResponseSBuilder;
import com.exp.FinWise.response.ResponseResume;
import com.exp.FinWise.usersOnBoarding.model.User;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/api/v1/resume/")
public class ResumeController {
    Logger log = LoggerFactory.getLogger(ResumeController.class);

    @Autowired
    ResumeService resumeService;

    @Autowired
    ResponseSBuilder responseSBuilder;

    @Autowired
    MailService mailService;

    @Autowired
    AuditService auditService;
    private void logData(String apiName, String data) {
        log.info("API called {}", apiName);
        log.info("Data: {}", data);
    }

    private void saveAudit(AuditLog auditLog){
        auditService.saveAudit(auditLog);
    }
    @PostMapping("/save")
    public ResponseResume saveResponse(@ModelAttribute SaveResponseRequest saveResponseRequest, @CurrentUser UserDetails customUserDetails){
        String apiname = "/api/v1/resume/save";
        String data = saveResponseRequest.toString();
        AuditLog auditLog= new AuditLog(customUserDetails.getUsername(),apiname,"api for save resume response from python team ", LocalDateTime.now());
        saveAudit(auditLog);
        logData(apiname, data);
//
//        try {
////            mailService.sendFinalResultMail(saveResponseRequest);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (TemplateException e) {
//            throw new RuntimeException(e);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }

        try {
            return resumeService.uploadAndStoreResumeData(saveResponseRequest, customUserDetails);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/show/all")
    public List<List<ResumeData>> showAllResumeData(@CurrentUser UserDetails customUserDetails){

        String apiname = "/api/v1/resume/show/all";
        logData(apiname, "Request to fetch all resume data.");
        AuditLog auditLog= new AuditLog(customUserDetails.getUsername(),apiname,"api for show all resume ", LocalDateTime.now());
        saveAudit(auditLog);
        return resumeService.getAllResumeData(customUserDetails);
    }

    @GetMapping("requirnment/{id}")
    public List<List<ResumeData>> getResumeById(@CurrentUser UserDetails userDetails, @PathVariable(name = "id") Long requirementId){
        String apiname = "/api/v1/resume/requirnment/" + requirementId;
        logData(apiname, "Request to fetch resume data for requirement with ID: " + requirementId);
        AuditLog auditLog= new AuditLog(userDetails.getUsername(),apiname,"Request to fetch resume data for requirement with ID: " + requirementId, LocalDateTime.now());
        saveAudit(auditLog);

        return resumeService.getResumeByRequirmentId(userDetails, requirementId);
    }

    @GetMapping("/get/all/requirement")
    public List<RequirementModel> getAllRequirement(@CurrentUser UserDetails userDetails){
        String apiname = "/api/v1/resume/get/all/requirement";
        logData(apiname, "Request to fetch all requirements.");
        AuditLog auditLog= new AuditLog(userDetails.getUsername(),apiname,"Request to fetch all requirements", LocalDateTime.now());
        saveAudit(auditLog);
        return resumeService.getAllRequirement(userDetails);
    }

    @PostMapping("/request/requirement")
    public ResponseResume saveRequirement(@RequestBody RequirementDTO requirementDTO, @CurrentUser UserDetails customUserDetails) {
        String apiname = "/api/v1/resume/request/requirement";
        logData(apiname, "Request to save a requirement." + requirementDTO.toString());
        AuditLog auditLog= new AuditLog(customUserDetails.getUsername(),apiname,"Request to save a requirement." + requirementDTO.toString(), LocalDateTime.now());
        saveAudit(auditLog);
//        resumeService.saveRequirement(requirementDTO, customUserDetails);
//        try {
//            mailService.sendrequirmentSubmittedSuccess(requirementDTO,customUserDetails);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (TemplateException e) {
//            throw new RuntimeException(e);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
        return resumeService.saveRequirement(requirementDTO, customUserDetails);
    }

    @GetMapping("mark/{id}")
    public ResponseResume updateResume(@CurrentUser UserDetails userDetails, @PathVariable(name = "id") Long resumeID){
        String apiname = "/api/v1/resume/mark/" + resumeID;
        logData(apiname, "Request to mark resume as contacted for ID: " + resumeID);

        AuditLog auditLog= new AuditLog(userDetails.getUsername(),apiname,"Request to mark resume as contacted for ID: " + resumeID , LocalDateTime.now());
        saveAudit(auditLog);
        return resumeService.markAsContacted(userDetails, resumeID);
    }


    @PostMapping("/add/comment")
    public ResponseResume addComment(@CurrentUser UserDetails userDetails , @RequestBody AddCommentDto addCommentDto) {
        String apiname = "/api/v1/resume/add/comment" + addCommentDto.getResumeId();
        logData(apiname, "Add a comment to the resume: " + addCommentDto.getComment());
        AuditLog auditLog= new AuditLog(userDetails.getUsername(),apiname,"Add a comment to the resume: " + addCommentDto.getComment(), LocalDateTime.now());
        saveAudit(auditLog);
        return resumeService.addComment(addCommentDto);
    }

    @GetMapping("/get/all/skills")
    public List<String> getAllITSkills(@CurrentUser UserDetails userDetails){
        String apiname = "/api/v1/resume/get/all/skills/";
        logData(apiname,"getting all sikkls ");
        AuditLog auditLog= new AuditLog(userDetails.getUsername(),apiname,"getting all sikkls ", LocalDateTime.now());
        saveAudit(auditLog);
        return resumeService.getAllITSkillsSet();
    }

    @GetMapping("/skills/{name}")
    public List<String> getSkillsByName(@CurrentUser UserDetails userDetails,@PathVariable("name") String name){
        String apiname = "/api/v1/resume/skills/" + name;
        logData(apiname,"getting  skills ");
        return resumeService.getSkillsByName(name);
    }

    @PostMapping("/search/resume")
    public ResumeData getResumeBySerach(@CurrentUser UserDetails userDetails, @RequestBody SearchDataDto searchDataDto){
        String apiname="/api/v1/get/search/resume";
        logData( apiname,"gating resume by searching");
        return resumeService.getResumeBySearch(searchDataDto);
    }

    @PostMapping("/page")
    public PagebleResumeResponseDto getAllResumeByPagenation(@CurrentUser UserDetails userDetails ,@RequestBody PaginationDto paginationDto){
        String apiname="/api/v1/resume/page";
        logData( apiname,"gating resume by searching");
        return resumeService.getAllResumeByPagenation(paginationDto);
    }

    @PostMapping("/otp")
    public ResponseResume getOtpForLogin(@CurrentUser UserDetails userDetails, @RequestBody OtpDto otpDto) {
        String apiname = "/api/v1/resume/otp/" + otpDto.getOtp();
        logData(apiname, "getting otp for login");
        AuditLog auditLog= new AuditLog(userDetails.getUsername(),apiname,"saving otp to DB ", LocalDateTime.now());
        saveAudit(auditLog);
        return resumeService.getOtpForLogin(otpDto);
    }

    @GetMapping("otp/received/{requirementId}")
    public ResponseResume receivedOtp(@PathVariable("requirementId") Long requirementId) {
        String apiname = "/api/v1/resume/otp/received" + requirementId;
        logData(apiname, "getting otp for db");
        AuditLog auditLog= new AuditLog("",apiname,"fetching the otp from DB for req id  "+ requirementId, LocalDateTime.now());
        saveAudit(auditLog);
        return resumeService.receivedOtp(requirementId);
    }

    @GetMapping("/keywords/{name}")
    public List<String> getKeywordsByName(@CurrentUser UserDetails userDetails,@PathVariable("name") String name){
        String apiname = "/api/v1/resume/keywords/" + name;
        logData(apiname,"getting  keywords ");
        return resumeService.getKeywordsByName(name);
    }

}
