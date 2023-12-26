package com.exp.FinWise.Resume.dto;

public class OtpDto {
    private String otp;
    private Long requirementId;

    public OtpDto(String otp, Long requirementId) {
        this.otp = otp;
        this.requirementId = requirementId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }
}
