package com.exp.FinWise.Resume.Service;
import com.exp.FinWise.Resume.Controller.FilesController;
import com.exp.FinWise.Resume.dto.*;

import com.exp.FinWise.Resume.model.*;
import com.exp.FinWise.Resume.repository.*;
import com.exp.FinWise.audit.model.AuditLog;
import com.exp.FinWise.audit.service.AuditService;
import com.exp.FinWise.configuration.AsyncMethodCall;
import com.exp.FinWise.response.ResponseSBuilder;
import com.exp.FinWise.response.ResponseResume;
import com.exp.FinWise.usersOnBoarding.model.User;
import com.exp.FinWise.usersOnBoarding.repository.UserRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    Logger log = LoggerFactory.getLogger(ResumeService.class);

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
    FilesController filesController;

    @Autowired
    AuditService auditService;

    @Autowired
    ITKeywordsSetRepository itKeywordsSetRepository;

    private void saveAudit(AuditLog auditLog){
        auditService.saveAudit(auditLog);
    }


    public ResponseResume saveRequirement (RequirementDTO requirementDTO, UserDetails customUserDetails){


            if (requirementDTO.getKeywords() == null || requirementDTO.getKeywords().isEmpty()) {
                return responseSBuilder.createResponse("Keywords cannot be null or empty.", HttpStatus.NO_CONTENT.value());
            }

            if (requirementDTO.getSkills() == null || requirementDTO.getSkills().isEmpty()) {
                return responseSBuilder.createResponse("Skills cannot be null or empty.", HttpStatus.NO_CONTENT.value());
            }

            if (requirementDTO.getExperienceMin() < 0 || requirementDTO.getExperienceMax() < 0 || requirementDTO.getExperienceMin() > requirementDTO.getExperienceMax()) {
                return responseSBuilder.createResponse("Invalid experience range.", HttpStatus.NO_CONTENT.value());
            }

            if (requirementDTO.getLocation() == null || requirementDTO.getLocation().isEmpty()) {
                return responseSBuilder.createResponse("Location cannot be null or empty.", HttpStatus.NO_CONTENT.value());
            }

            if (requirementDTO.getMin_salary() < 0) {
                return responseSBuilder.createResponse("Annual salary cannot be negative.", HttpStatus.NO_CONTENT.value());
            }

            User user = getUser(customUserDetails);
            RequirementModel requirement = saveRequirment(requirementDTO, user);
            log.info("Saved info {}", requirement);
//            asyncMethodCall.callToPythonApi(requirement);
        AuditLog auditLog= new AuditLog(customUserDetails.getUsername(),"requirment saved in DB called an api fo python team to proceed it further  ","data saved success"+ requirement.getKeywords(), LocalDateTime.now());
        saveAudit(auditLog);

        return responseSBuilder.createResponse(requirement);
        }


        @NotNull
        public User getUser (UserDetails customUserDetails){
            User user = userRepository.findByUsername(customUserDetails.getUsername()).orElseThrow();
            return user;
        }

        @NotNull
        private RequirementModel saveRequirment (RequirementDTO requirementDTO, User user){
            RequirementModel requirement = new RequirementModel();
            requirement.setKeywords(requirementDTO.getKeywords());
            requirement.setSkills(requirementDTO.getSkills());
            requirement.setExperienceMin(requirementDTO.getExperienceMin());
            requirement.setExperienceMax(requirementDTO.getExperienceMax());
            requirement.setLocation(requirementDTO.getLocation());
            requirement.setMinSalary(requirementDTO.getMin_salary());
            requirement.setMaxSalary(requirementDTO.getMax_salary());
            requirement.setUser(user);
            requirement.setResumeCount(requirementDTO.getNumberOfResumes());
            requirement.setJobDescription(requirementDTO.getJob_description());
            requirementRepository.save(requirement);
            RequirementResponseMapper requirementResponseMapper = new RequirementResponseMapper();
            requirementResponseMapper.setRequirementModel(requirement);
            requirementResponseMapper.setTimeOfRequest(LocalDate.now());
            requirementResponseMapperRepo.save(requirementResponseMapper);
            return requirement;
        }


        public ResponseResume uploadAndStoreResumeData (SaveResponseRequest file, UserDetails customUserDetails) throws
        IOException {
            Optional<RequirementModel> requirementModel = requirementRepository.findById(file.getRequirnmentId());
            List<RequirementResponseMapper> requirementId = requirementResponseMapperRepo.findByRequirementModel(requirementModel.get());
            RequirementResponseMapper requirementResponseMapper = requirementId.get(0);
            requirementResponseMapper.setTimeOfResponse(LocalDate.now());
            requirementResponseMapperRepo.save(requirementResponseMapper);

            Workbook workbook = new XSSFWorkbook(file.getFile().getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                ResumeDataDto resumeDataDto = new ResumeDataDto();

                resumeDataDto.setDesignation(row.getCell(0).getStringCellValue());
                resumeDataDto.setQualification(row.getCell(1).getStringCellValue());
                resumeDataDto.setPreferLocations(row.getCell(2).getStringCellValue());
                resumeDataDto.setEmployeeName(row.getCell(3).getStringCellValue());
                resumeDataDto.setCurrentLocation(row.getCell(4).getStringCellValue());
                resumeDataDto.setCurrent_package(row.getCell(5).getStringCellValue());
                resumeDataDto.setNotice_period(row.getCell(6).getStringCellValue());

                resumeDataDto.setNotice_period(row.getCell(7).getStringCellValue());
                resumeDataDto.setContact_details(row.getCell(8).getStringCellValue());

                StringBuilder workExperienceBuilder = new StringBuilder();
                for (int i = 9; i < row.getPhysicalNumberOfCells()-3; i++) {
                        Cell cell = row.getCell(i);
                        String cellValue = cell.getStringCellValue();
                        if (cellValue != null && !cellValue.isEmpty()) {
                            workExperienceBuilder.append(cellValue);
                        }
                }
                resumeDataDto.setWork_experience(workExperienceBuilder.toString());
                resumeDataDto.setEmail(row.getCell(10).getStringCellValue());
                resumeDataDto.setLinks(row.getCell(11).getStringCellValue());
                resumeDataDto.setSimilarityScore(String.valueOf(row.getCell(12).getNumericCellValue()));
                ResumeData resumeData = new ResumeData();
                resumeData.setContected(false);
                resumeData.setLinks(resumeDataDto.getLinks());
                resumeData.setDesignation(resumeDataDto.getDesignation());
                resumeData.setLinks(resumeDataDto.getLinks());
                resumeData.setEmail(resumeDataDto.getEmail());
                resumeData.setCurrent_package(resumeDataDto.getCurrent_package());
                resumeData.setContact_details(resumeDataDto.getContact_details());
                resumeData.setCurrentLocation(resumeDataDto.getCurrentLocation());
                resumeData.setEmployee_experience(resumeDataDto.getEmployee_experience());
                resumeData.setNotice_period(resumeDataDto.getNotice_period());
                resumeData.setEmployeeName(resumeDataDto.getEmployeeName());
                resumeData.setRequirementResponseMapper(requirementResponseMapper);
                resumeData.setPreferLocations(resumeDataDto.getPreferLocations());
                resumeData.setQualification(resumeDataDto.getQualification());
                resumeData.setSimilarityScore(resumeDataDto.getSimilarityScore());
                resumeData.setWork_experience(resumeDataDto.getWork_experience().toString());
                resumeDataRepository.save(resumeData);



            }

            AuditLog auditLog= new AuditLog("python","final excel  data save method ","data saved success", LocalDateTime.now());
            saveAudit(auditLog);
            workbook.close();
            return responseSBuilder.createResponse("ok");
        }


        public List<List<ResumeData>> getAllResumeData (UserDetails customUserDetails){
            User user = getUser(customUserDetails);
            List<RequirementModel> modelOptional = requirementRepository.findAllByUser(user);
            List<RequirementResponseMapper> responseMapper = requirementResponseMapperRepo.findByRequirementModel(modelOptional.get(0));
            List<List<ResumeData>> resumeDataList = new ArrayList<>();
            List<ResumeData> listresumeData = null;
            for (RequirementResponseMapper rrm : responseMapper) {
                listresumeData= resumeDataRepository.findAllByRequirementResponseMapper_MapperId(rrm.getMapperId());
            }
            List<ResumeData> rds= new ArrayList<>();
            for (ResumeData rd:listresumeData){
                String url =filesController.getFileUrl(rd.getLinks());
                rd.setLinks(url);
                rds.add(rd);
            }
            resumeDataList.add(rds);
            return resumeDataList;
        }

        public List<ResumeData> getAllResumeDatas (UserDetails customUserDetails){
            User user = getUser(customUserDetails);
            List<RequirementResponseMapper> responseMappers = requirementResponseMapperRepo
                    .findByRequirementModel_User(user);

            return responseMappers.stream()
                    .map(RequirementResponseMapper::getMapperId)
                    .flatMap(mapperId -> resumeDataRepository
                            .findAllByRequirementResponseMapper_MapperId(mapperId).stream())
                    .collect(Collectors.toList());
        }


        public List<List<ResumeData>> getResumeByRequirmentId (UserDetails userDetails, Long requirmnetId){
            Optional<RequirementModel> requirementModel = requirementRepository.findById(requirmnetId);
            List<RequirementResponseMapper> responseMapper = requirementResponseMapperRepo.findByRequirementModel(requirementModel.get());
            List<List<ResumeData>> resumeDataList = new ArrayList<>();
            List<ResumeData> listresumeData = null;
            for (RequirementResponseMapper rrm : responseMapper) {
                listresumeData= resumeDataRepository.findAllByRequirementResponseMapper_MapperId(rrm.getMapperId());
//                resumeDataList.add(resumeData);
            }

            List<ResumeData> rds= new ArrayList<>();

            for (ResumeData rd:listresumeData){
                String url =filesController.getFileUrl(rd.getLinks());
                rd.setLinks(url);
                rds.add(rd);
            }
            resumeDataList.add(rds);
            return resumeDataList;
        }

        public List<RequirementModel> getAllRequirement (UserDetails userDetails){
            return requirementRepository.findAllByUser(getUser(userDetails));

        }

        public ResponseResume markAsContacted (UserDetails userDetails, Long resumeID){
            Optional<ResumeData> optionalResumeData = resumeDataRepository.findById(resumeID);
            ResumeData resumeData = null;
            if (!optionalResumeData.isEmpty()) {
                resumeData = optionalResumeData.get();
                resumeData.setContected(true);
                resumeDataRepository.save(resumeData);
                System.out.println("mark as completed for :" + resumeID);
            }
            return responseSBuilder.createResponse("mark as contacted");
        }


        public ResponseResume addComment (AddCommentDto addCommentDto) {
            log.info("inside add comment method");
            ResumeData resumeData = new ResumeData();
            Optional<ResumeData> resumeDataOptional = resumeDataRepository.findById(addCommentDto.getResumeId());
            if (resumeDataOptional.isPresent()) {
                resumeData = resumeDataOptional.get();
            }
            resumeData.setComment(addCommentDto.getComment());
            resumeDataRepository.save(resumeData);
            return responseSBuilder.createResponse("Add comment");
        }
            public List<String> getAllITSkillsSet () {
                List<ITSkillsSet> itSkillsSets = itSkillsSetRepository.findAll();
                System.out.println("get data " + itSkillsSets.size());
                List<String> response = new ArrayList<>();
                for (ITSkillsSet set : itSkillsSets) {
                    System.out.println(set);
                    response.add(set.getSkills());

                }
                return response;
            }

        public List<String> getSkillsByName (String name){
            List<ITSkillsSet> itSkillsSets = itSkillsSetRepository.findBySkillsStartingWith(name.toUpperCase(Locale.ROOT),name.toLowerCase(Locale.ROOT));
            List<String> response = new ArrayList<>();
            for (ITSkillsSet set : itSkillsSets) {
                System.out.println(set);
                response.add(set.getSkills());

            }
            return response;

        }

    public ResumeData getResumeBySearch(SearchDataDto searchDataDto) {
            // add validation on name
            List<ResumeData> resumeData=resumeDataRepository.findByEmployeeNameContainingOrEmailContaining(searchDataDto.getName(),searchDataDto.getEmail());
            List<ResumeData> resumeDataList = new ArrayList<>();
            // if size is zero that show no resume found
        if (!resumeData.isEmpty()) {
            ResumeData firstResume = resumeData.get(0);
            log.info("search resume data: " + firstResume);
            return firstResume; // Return the first element
        } else {
            for (ResumeData search : resumeData) {
                log.info("search resume data : " + search.getEmail());
                resumeDataList.add(search);
            }
            return null;
        }
    }

    public PagebleResumeResponseDto getAllResumeByPagenation(PaginationDto paginationDto) {

        Pageable pageable = PageRequest.of(paginationDto.getPageNo(), paginationDto.getPageSize());
        Page<ResumeData> resumeData= resumeDataRepository.findAll(pageable);
        List<ResumeDataDto> resumeDataList = new ArrayList<>();

        for(ResumeData sortdata:resumeData){
            ResumeDataDto resumeDataDto=new ResumeDataDto(sortdata);
            log.info("search resume data : "+sortdata);
            resumeDataList.add(resumeDataDto);
        }
        PagebleResumeResponseDto pagebleResumeResponseDto= new PagebleResumeResponseDto(resumeDataList);
        List<ResumeDataDto> resumeDataDtos=pagebleResumeResponseDto.resumeDataDtos;
        List<ResumeDataDto> responseList= new ArrayList<>();
        for (ResumeDataDto rrs:resumeDataDtos){
            String url =filesController.getFileUrl(rrs.getLinks());
            rrs.setLinks(url);
            responseList.add(rrs);
        }

        pagebleResumeResponseDto.setResumeDataDtos(resumeDataDtos);

        return pagebleResumeResponseDto;

    }

    public ResponseResume getOtpForLogin(OtpDto otpDto) {

        log.info("otp: {}", otpDto.getOtp());
        Optional <RequirementModel> optionalRequirementModel= requirementRepository.findById(otpDto.getRequirementId());
        RequirementModel requirementModel=optionalRequirementModel.get();
        List<RequirementResponseMapper>  requirementResponseMapper = requirementResponseMapperRepo.findByRequirementModel(requirementModel);
        RequirementResponseMapper responseMapper=requirementResponseMapper.get(0);
        responseMapper.setOtp(otpDto.getOtp());
        requirementResponseMapperRepo.save(responseMapper);

        return responseSBuilder.createResponse("Successfully saved OTP");
        }

    public ResponseResume receivedOtp(Long requirementId) {
        Optional<RequirementModel> optionalRequirementModel = requirementRepository.findById(requirementId);

        RequirementModel requirementModel = optionalRequirementModel.orElse(null);

        if (requirementModel == null) {
            return responseSBuilder.createResponse("Requirement not found",400);
        }
        List<RequirementResponseMapper> requirementResponseMappers = requirementResponseMapperRepo.findByRequirementModel(requirementModel);
        if (!requirementResponseMappers.isEmpty()) {
            RequirementResponseMapper responseMapper = requirementResponseMappers.get(0);

            if (responseMapper.getOtp() == null || responseMapper.getOtp().isEmpty()) {

                return responseSBuilder.createResponse("Otp not received",400);
            } else {
                OtpDto otpDto=new OtpDto(responseMapper.getOtp(),requirementId);
                return responseSBuilder.createResponse(otpDto);
            }
        } else {
            return responseSBuilder.createResponse("No response mappers found for the requirement");
        }
    }

    public List<String> getKeywordsByName(String name) {

        List<ITKeywordsSet> itSkillsSets = itKeywordsSetRepository.findBySkillsStartingWith(name.toUpperCase(Locale.ROOT),name.toLowerCase(Locale.ROOT));
        List<String> response = new ArrayList<>();
        for (ITKeywordsSet set : itSkillsSets) {
            System.out.println(set);
            response.add(set.getSkills());

        }
        return response;
    }
}