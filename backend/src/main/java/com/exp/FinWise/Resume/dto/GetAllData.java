package com.exp.FinWise.Resume.dto;

import com.exp.FinWise.Resume.model.ResumeData;

import java.util.List;

public class GetAllData {
    RequirementDTO requirementDTO;
    List<ResumeData> resumeData;

    public RequirementDTO requirementDTO() {
        return requirementDTO;
    }

    public GetAllData setRequirementDTO(RequirementDTO requirementDTO) {
        this.requirementDTO = requirementDTO;
        return this;
    }

    public List<ResumeData> resumeData() {
        return resumeData;
    }

    public GetAllData setResumeData(List<ResumeData> resumeData) {
        this.resumeData = resumeData;
        return this;
    }

    public GetAllData(RequirementDTO requirementDTO, List<ResumeData> resumeData) {
        this.requirementDTO = requirementDTO;
        this.resumeData = resumeData;
    }

    @Override
    public String toString() {
        return "GetAllData{" +
                "requirementDTO=" + requirementDTO +
                ", resumeData=" + resumeData +
                '}';
    }
}
