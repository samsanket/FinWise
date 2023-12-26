package com.exp.FinWise.Resume.dto;


import com.exp.FinWise.Resume.model.RequirementModel;
import jakarta.validation.constraints.NotBlank;
public class RequirementDTO {

    @NotBlank
    private String keywords;

    @NotBlank
    private String skills;

    @NotBlank
    private Long experienceMin;

    @NotBlank
    private Long experienceMax;

    @NotBlank
    private String location;

    @NotBlank
    private Float min_salary;


    private Float max_salary;

    private Long numberOfResumes;

    private  String job_description;
    

    private  Long requirement_id;


    public Long getRequirement_id() {
        return requirement_id;
    }

    public void setRequirement_id(Long requirement_id) {
        this.requirement_id = requirement_id;
    }

    @Override
    public String toString() {
        return "RequirementDTO{" +
                "keywords='" + keywords + '\'' +
                ", skills='" + skills + '\'' +
                ", experienceMin=" + experienceMin +
                ", experienceMax=" + experienceMax +
                ", location='" + location + '\'' +
                ", min_salary=" + min_salary +
                ", max_salary=" + max_salary +
                ", numberOfResumes=" + numberOfResumes +
                ", job_description='" + job_description + '\'' +
                ", requirement_id=" + requirement_id +
                '}';
    }

    public Float getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(Float max_salary) {
        this.max_salary = max_salary;
    }

    public Long getResume_count() {
        return numberOfResumes;
    }

    public void setResume_count(Long resume_count) {
        this.numberOfResumes = resume_count;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public Long getNumberOfResumes() {
        return numberOfResumes;
    }

    public void setNumberOfResumes(Long numberOfResumes) {
        this.numberOfResumes = numberOfResumes;
    }

    public RequirementDTO(){

    }
    public RequirementDTO(RequirementModel requirementModel) {
        this.setMin_salary(requirementModel.getMinSalary());
        this.setExperienceMax(requirementModel.getExperienceMax());
        this.setSkills(requirementModel.getSkills());
        this.setLocation(requirementModel.getLocation());
        this.setKeywords(requirementModel.getKeywords());
        this.setExperienceMin(requirementModel.getExperienceMin());
        this.job_description= requirementModel.getJobDescription();
        this.numberOfResumes=requirementModel.getResumeCount();
        this.requirement_id=requirementModel.getRequirementId();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Long getExperienceMin() {
        return experienceMin;
    }

    public void setExperienceMin(Long experienceMin) {
        this.experienceMin = experienceMin;
    }

    public Long getExperienceMax() {
        return experienceMax;
    }

    public void setExperienceMax(Long experienceMax) {
        this.experienceMax = experienceMax;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(Float min_salary) {
        this.min_salary = min_salary;
    }
}
