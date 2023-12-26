/*
 * Copyright (c) 2023.
 * Sanket Deshpande . All rights reserved
 */

package com.exp.FinWise.Resume.model;

import jakarta.persistence.*;


@Entity
@Table(name = "scrape_data")
public class ScrapeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrape_data_id")
    private Long scrapeDataId;


    @ManyToOne
    @JoinColumn(name = "requirement_id")
    private RequirementModel requirement;

    private String designation;
    private String qualification;
    private String preferLocations;
    private String employeeName;
    private String currentLocation;
    private String currentPackage;
    private String employeeExperience;
    private String noticePeriod;
    private String contactDetails;

    @Column(name = "work_experience", columnDefinition = "TEXT")
    private String workExperience;
    private String email;
//    private double similarityScore;

    public Long getScrapeDataId() {
        return scrapeDataId;
    }

    public void setScrapeDataId(Long scrapeDataId) {
        this.scrapeDataId = scrapeDataId;
    }

    public RequirementModel getRequirement() {
        return requirement;
    }

    public void setRequirement(RequirementModel requirement) {
        this.requirement = requirement;
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

    public String getCurrentPackage() {
        return currentPackage;
    }

    public void setCurrentPackage(String currentPackage) {
        this.currentPackage = currentPackage;
    }

    public String getEmployeeExperience() {
        return employeeExperience;
    }

    public void setEmployeeExperience(String employeeExperience) {
        this.employeeExperience = employeeExperience;
    }

    public String getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(String noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public double getSimilarityScore() {
//        return similarityScore;
//    }
//
//    public void setSimilarityScore(double similarityScore) {
//        this.similarityScore = similarityScore;
//    }
}