package com.exp.FinWise.Resume.model;

import com.exp.FinWise.Resume.dto.ResumeDataDto;
import jakarta.persistence.*;

import java.util.List;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "resumeDetails")
public class ResumeData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;

    private String qualification;

    private String preferLocations;

    private String employeeName;

    private String currentLocation;

    private String current_package;

    private String employee_experience;

    private String notice_period;

    private  String contact_details;

    @Column(name = "links")
    private String links;

    @Column(name = "contacted")
    private Boolean isContected;

    @Column(name = "work_experience", columnDefinition = "TEXT")
    private String work_experience;

    private String email;


    @ManyToOne
    @JoinColumn(name = "mapper_id")
    private RequirementResponseMapper requirementResponseMapper;


    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    private String similarityScore;

    public String getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(String similarityScore) {
        this.similarityScore = similarityScore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getContected() {
        return isContected;
    }

    public void setContected(Boolean contected) {
        isContected = contected;
    }

    public RequirementResponseMapper getRequirementResponseMapper() {
        return requirementResponseMapper;
    }

    public void setRequirementResponseMapper(RequirementResponseMapper requirementResponseMapper) {
        this.requirementResponseMapper = requirementResponseMapper;
    }



    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String email() {
        return email;
    }
    public ResumeData(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ResumeData(String email, String designation, String contactDetails, String currentPackage, String qualification, String employeeName, String employeeExperience, String workExperience, String preferLocations, String noticePeriod, String currentLocation, RequirementResponseMapper requirementResponseMapper, String links) {
        this.email = email;
        this.designation = designation;
        this.contact_details = contactDetails;
        this.current_package = currentPackage;
        this.qualification = qualification;
        this.employeeName = employeeName;
        this.employee_experience = employeeExperience;
        this.work_experience = workExperience;
        this.preferLocations = preferLocations;
        this.notice_period = noticePeriod;
        this.currentLocation = currentLocation;
        this.requirementResponseMapper=requirementResponseMapper;
        this.links=links;
        this.setContected(false);
    }

    @Override
    public String toString() {
        return "ResumeData{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", qualification='" + qualification + '\'' +
                ", preferLocations='" + preferLocations + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", current_package='" + current_package + '\'' +
                ", employee_experience='" + employee_experience + '\'' +
                ", notice_period='" + notice_period + '\'' +
                ", contact_details='" + contact_details + '\'' +
                ", links='" + links + '\'' +
                ", isContected=" + isContected +
                ", work_experience='" + work_experience + '\'' +
                ", email='" + email + '\'' +
                ", requirementResponseMapper=" + requirementResponseMapper +
                ", comment='" + comment + '\'' +
                ", similarityScore='" + similarityScore + '\'' +
                '}';
    }
    public  ResumeData(ResumeDataDto resumeDataDto){
        this.email=resumeDataDto.getEmail();
        this.designation=resumeDataDto.getDesignation();
        this.contact_details=resumeDataDto.getContact_details();
        this.current_package=resumeDataDto.getCurrent_package();
        this.qualification=resumeDataDto.getQualification();
        this.employee_experience=resumeDataDto.getEmployee_experience();
        this.work_experience=resumeDataDto.getWork_experience();
        this.preferLocations=resumeDataDto.getPreferLocations();
        this.notice_period=resumeDataDto.getNotice_period();
        this.requirementResponseMapper=resumeDataDto.getRequirementResponseMapper();
        this.links=resumeDataDto.getLinks();

        this.setContected(false);
    }

}
