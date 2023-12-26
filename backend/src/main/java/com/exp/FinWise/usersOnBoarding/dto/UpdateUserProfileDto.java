package com.exp.FinWise.usersOnBoarding.dto;




public class UpdateUserProfileDto {
    private String address;
    private String dateOfBirth;
    private String education;
    private String collegeName;
    private String percentage;
    private String branch;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String  percentage) {
        this.percentage = percentage;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "UpdateUserProfileDto{" +
                "address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", education='" + education + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", percentage=" + percentage +
                ", branch='" + branch + '\'' +
                '}';
    }

    public UpdateUserProfileDto(String address, String dateOfBirth, String education, String collegeName, String percentage, String branch) {
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.education = education;
        this.collegeName = collegeName;
        this.percentage = percentage;
        this.branch = branch;
    }
}
