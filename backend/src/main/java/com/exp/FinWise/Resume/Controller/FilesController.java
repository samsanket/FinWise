/*
 * Copyright (c) 2023.
 * Sanket Deshpande . All rights reserved
 */

package com.exp.FinWise.Resume.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.exp.FinWise.Resume.Service.FilesStorageServiceImpl;
import com.exp.FinWise.Resume.dto.ResumeDataDto;
import com.exp.FinWise.Resume.model.*;
import com.exp.FinWise.Resume.repository.*;
import com.exp.FinWise.audit.model.AuditLog;
import com.exp.FinWise.audit.service.AuditService;
import com.exp.FinWise.configuration.AsyncMethodCall;
import com.exp.FinWise.response.ResponseMessage;
import com.exp.FinWise.response.ResponseSBuilder;
import com.exp.FinWise.usersOnBoarding.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


@Controller
@CrossOrigin("*")
public class FilesController {

    Logger log = LoggerFactory.getLogger(FilesController.class);


    @Autowired
    RequirementRepository requirementRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RequirementResponseMapperRepo requirementResponseMapperRepo;

    @Autowired
    ResumeDataRepository resumeDataRepository;

    @Autowired
    ResponseSBuilder responseSBuilder;


    @Autowired
    AsyncMethodCall asyncMethodCall;


    @Autowired
    private ITSkillsSetRepository itSkillsSetRepository;

    @Autowired
    FilesStorageServiceImpl storageService;

    @Autowired
    ScrapeDataRepo scrapeDataRepo;

    @Autowired
    AuditService auditService;

    private String uploadPath = "/uploads";
    private final Path rootLocation = Paths.get("uploads");

    public void logData(String api, String data){
        log.info("Calling api {} with data/info {}",api,data);
    }

    private void saveAudit(AuditLog auditLog){
        auditService.saveAudit(auditLog);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("email") String email,
            @RequestParam("similarityScore") String similarityScore,
            @RequestParam("requirmentId") Long requirementId
    ) {
        logData("/upload  ", requirementId.toString());
        AuditLog auditLog = new AuditLog("python", "/upload", "api to save the final data with file email" + email, LocalDateTime.now());
        saveAudit(auditLog);

        ScrapeData scrapeData = null;
        Optional<ScrapeData> scrapeDataOptional = scrapeDataRepo.findByEmail(email);
        if (scrapeDataOptional.isPresent()) {
            scrapeData = scrapeDataOptional.get();
        }
        ResumeDataDto resumeDto = new ResumeDataDto(scrapeData);
        resumeDto.setSimilarityScore(similarityScore);

        String message = "";
        Optional<RequirementModel> optionalRequirementModel = requirementRepository.findById(requirementId);
        RequirementModel requirementModel = null;
        if (optionalRequirementModel.isPresent()) {
            requirementModel = optionalRequirementModel.get();
        }
        RequirementResponseMapper requirementResponseMapper = requirementResponseMapperRepo.findByRequirementModel(requirementModel).get(0);
        resumeDto.setRequirementResponseMapper(requirementResponseMapper);

        try {
            // Ensure that the directory exists
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            filename = filename.toLowerCase();
            String fileExtension = FilenameUtils.getExtension(filename); // Using Apache Commons IO
            String UUID = String.valueOf(System.currentTimeMillis());
            String replaced = UUID + "." + fileExtension;
            log.info("replced file name "+replaced);

           Files.copy(file.getInputStream(), this.rootLocation.resolve(replaced),
                    StandardCopyOption.REPLACE_EXISTING);
            Files.deleteIfExists(this.rootLocation.resolve(file.getOriginalFilename()));
            storageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            resumeDto.setLinks(replaced);
            ResumeData resumeData = new ResumeData(resumeDto);
            resumeDataRepository.save(resumeData);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            e.printStackTrace();
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        AuditLog auditLog= new AuditLog("react","/files","calling api to get files", LocalDateTime.now());
        saveAudit(auditLog);
        logData("/files ","all files");
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        logData("/files  ",filename);
        AuditLog auditLog= new AuditLog("react","/files","calling api to get files"+ filename, LocalDateTime.now());
        saveAudit(auditLog);
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/files/{filename:.+}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String filename) {

        AuditLog auditLog= new AuditLog("react","/files","calling api to get files"+ filename, LocalDateTime.now());
        saveAudit(auditLog);
        String message = "";

        try {
            boolean existed = storageService.delete(filename);

            if (existed) {
                message = "Delete the file successfully: " + filename;
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }

            message = "The file does not exist!";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not delete the file: " + filename + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/resumes/file/{filename}")
    public ResponseEntity<Resource> getactualfile(@PathVariable String filename) {
        AuditLog auditLog= new AuditLog("react","/files","calling api to get files"+ filename, LocalDateTime.now());
        saveAudit(auditLog);
        logData("/resume/files ",  filename);
        log.info("Inside api /resumes/file/{}",filename);
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);


    }


    @GetMapping("/files/url/{fileName}")
    public ResponseEntity<FileInfo> getFileInfo(@PathVariable String fileName) {
        AuditLog auditLog= new AuditLog("react","/files/url/","calling api to get files"+ fileName, LocalDateTime.now());
        saveAudit(auditLog);
        logData("/files/url/  " , fileName);
        try {

            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", fileName).build().toString();
            FileInfo fileInfo = new FileInfo(fileName, url);
            return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public String getFileUrl(String fileName){

        logData("getting file url for file " , fileName);
        return  MvcUriComponentsBuilder
                .fromMethodName(FilesController.class, "getFile", fileName).build().toString();
    }

}

