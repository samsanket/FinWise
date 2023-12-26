package com.exp.FinWise.Resume.dto;

import com.exp.FinWise.Resume.model.ResumeData;
import com.exp.FinWise.Resume.model.RequirementResponseMapper;
import com.exp.FinWise.Resume.model.ResumeData;
import com.exp.FinWise.Resume.model.ScrapeData;


public class ResumeDataDto {
    private String designation;

    private String qualification;

    private String preferLocations;

    private String employeeName;

    private String currentLocation;

    private String current_package;

    private String employee_experience;

    private String notice_period;

    private  String contact_details;

    private  String work_experience;

    private String email;

    private Boolean iscontacted;

    private String similarityScore;

    private Long requirmentId;

    private RequirementResponseMapper requirementResponseMapper;



    public RequirementResponseMapper getRequirementResponseMapper() {
        return requirementResponseMapper;
    }

    public void setRequirementResponseMapper(RequirementResponseMapper requirementResponseMapper) {
        this.requirementResponseMapper = requirementResponseMapper;
    }

    public Long getRequirmentId() {
        return requirmentId;
    }

    public void setRequirmentId(Long requirmentId) {
        this.requirmentId = requirmentId;
    }

    public String getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(String similarityScore) {
        this.similarityScore = similarityScore;
    }

    public Boolean getIscontacted() {
        return iscontacted;
    }

    public void setIscontacted(Boolean iscontacted) {
        this.iscontacted = iscontacted;
    }

    public String getLinks() {
        return links;
    }

    public String links() {
        return links;
    }

    public ResumeDataDto setLinks(String links) {
        this.links = links;
        return this;
    }

    private String links;


    public  ResumeDataDto(){

    }
    public ResumeDataDto(ResumeData resumeData) {
        this.setDesignation(resumeData.getDesignation());
        this.setQualification(resumeData.getQualification());
        this.setPreferLocations(resumeData.getPreferLocations());
        this.setEmployeeName(resumeData.getEmployeeName());
        this.setCurrent_package(resumeData.getCurrent_package());
        this.setEmployee_experience(resumeData.getEmployee_experience());
        this.setNotice_period(resumeData.getNotice_period());
        this.setContact_details(resumeData.getContact_details());
        this.setWork_experience(resumeData.getWork_experience());
        this.setEmail(resumeData.getEmail());
        this.setLinks(resumeData.getLinks());
    }
    public ResumeDataDto(ScrapeData scrapeData){
        this.setDesignation(scrapeData.getDesignation());
        this.setQualification(scrapeData.getQualification());
        this.setPreferLocations(scrapeData.getPreferLocations());
        this.setEmployeeName(scrapeData.getEmployeeName());
        this.setCurrent_package(scrapeData.getCurrentPackage());
        this.setEmployee_experience(scrapeData.getEmployeeExperience());
        this.setNotice_period(scrapeData.getNoticePeriod());
        this.setContact_details(scrapeData.getContactDetails());
        this.setWork_experience(scrapeData.getWorkExperience());
        this.setEmail(scrapeData.getEmail());
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPreferLocations() {
        return preferLocations;
    }

    public void setPreferLocations(String preferLocations) {
        this.preferLocations = preferLocations;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCurrent_package() {
        return current_package;
    }

    public void setCurrent_package(String current_package) {
        this.current_package = current_package;
    }

    public String getEmployee_experience() {
        return employee_experience;
    }

    public void setEmployee_experience(String employee_experience) {
        this.employee_experience = employee_experience;
    }

    public String getNotice_period() {
        return notice_period;
    }

    public void setNotice_period(String notice_period) {
        this.notice_period = notice_period;
    }

    public String getContact_details() {
        return contact_details;
    }

    public void setContact_details(String contact_details) {
        this.contact_details = contact_details;
    }

    public String getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(String work_experience) {
        this.work_experience = work_experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}