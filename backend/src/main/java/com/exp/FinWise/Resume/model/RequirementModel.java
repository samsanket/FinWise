package com.exp.FinWise.Resume.model;

import com.exp.FinWise.usersOnBoarding.model.User;
import jakarta.persistence.*;


@Table(name = "requirement")
@Entity
public class RequirementModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requirement_id")
    private Long requirementId;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "skills")
    private String skills;

    @Column(name = "experience_min")
    private Long experienceMin;

    @Column(name = "experience_max")
    private Long experienceMax;

    @Column(name = "location")
    private String location;

    @Column(name = "min_salary")
    private Float minSalary;

    @Column(name = "max_salary")
    private Float maxSalary;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "resume_count")
    private Long resumeCount;

    @Column(name = "job_description")
    private String jobDescription;

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Float getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Float maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Long getResumeCount() {
        return resumeCount;
    }

    public void setResumeCount(Long resumeCount) {
        this.resumeCount = resumeCount;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
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

    public Float getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Float minSalary) {
        this.minSalary = minSalary;
    }

    @Override
    public String toString() {
        return "RequirementModel{" + "requirementId=" + requirementId + ", keywords='" + keywords + '\'' + ", skills='" + skills + '\'' + ", experienceMin=" + experienceMin + ", experienceMax=" + experienceMax + ", location='" + location + '\'' + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary + ", resumeCount=" + resumeCount + ", jobDescription='" + jobDescription + '\'' + '}';
    }

}
