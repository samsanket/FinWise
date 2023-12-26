package com.exp.FinWise.Resume.model;



import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "requirement_response_mapper")
public class RequirementResponseMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "mapper_id")
    private Long mapperId;


    @Column(name = "time_of_request")
    private LocalDate timeOfRequest;


    @ManyToOne
    @JoinColumn(name = "requirement_id")
    private RequirementModel requirementModel;

    @Column(name = "time_of_response")
    private LocalDate timeOfResponse;

    @Column(name = "isCompleted")
    private Boolean isCompleted;

    @Column(name = "otp")
    private String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Long getMapperId() {
        return mapperId;
    }

    public void setMapperId(Long mapperId) {
        this.mapperId = mapperId;
    }

    public LocalDate getTimeOfRequest() {
        return timeOfRequest;
    }

    public void setTimeOfRequest(LocalDate timeOfRequest) {
        this.timeOfRequest = timeOfRequest;
    }

    public RequirementModel getRequirementModel() {
        return requirementModel;
    }

    public void setRequirementModel(RequirementModel requirementModel) {
        this.requirementModel = requirementModel;
    }

    public LocalDate getTimeOfResponse() {
        return timeOfResponse;
    }

    public void setTimeOfResponse(LocalDate timeOfResponse) {
        this.timeOfResponse = timeOfResponse;
    }

    public RequirementResponseMapper(Long mapperId, LocalDate timeOfRequest, RequirementModel requirementModel, LocalDate timeOfResponse, Boolean isCompleted, String otp) {
        this.mapperId = mapperId;
        this.timeOfRequest = timeOfRequest;
        this.requirementModel = requirementModel;
        this.timeOfResponse = timeOfResponse;
        this.isCompleted = false;
        this.otp=otp;
    }

    public RequirementResponseMapper() {
    }
}
