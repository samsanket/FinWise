//package com.vidya.leap.Resume.model;
//
//
//import jakarta.persistence.*;
//import java.util.Arrays;
//
//@Entity
//@Table(name = "response")
//public class ResumeResponse {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long resumeId;
//
//    private String title;
//
//    @Lob // Use @Lob annotation to indicate that this field should be stored as binary large object (BLOB)
//    private byte[] file;
//
//
//    @Override
//    public String toString() {
//        return "ResumeTable{" +
//                "resumeId=" + resumeId +
//                ", title='" + title + '\'' +
//                ", file=" + Arrays.toString(file) +
//                '}';
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "requirement_id")
//    private RequirementModel requirementModel;
//
//    public RequirementModel getRequirementModel() {
//        return requirementModel;
//    }
//
//    public void setRequirementModel(RequirementModel requirementModel) {
//        this.requirementModel = requirementModel;
//    }
//
//    public Long getResumeId() {
//        return resumeId;
//    }
//
//    public void setResumeId(Long resumeId) {
//        this.resumeId = resumeId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public byte[] getFile() {
//        return file;
//    }
//
//    public void setFile(byte[] file) {
//        this.file = file;
//    }
//}
//
//
