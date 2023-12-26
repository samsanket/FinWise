package com.exp.FinWise.Resume.dto;

import java.util.List;

public class PagebleResumeResponseDto {
    public List<ResumeDataDto> resumeDataDtos;

    public List<ResumeDataDto> getResumeDataDtos() {
        return resumeDataDtos;
    }

    public void setResumeDataDtos(List<ResumeDataDto> resumeDataDtos) {
        this.resumeDataDtos = resumeDataDtos;
    }

    public PagebleResumeResponseDto(List<ResumeDataDto> resumeDataList) {
      this.resumeDataDtos=resumeDataList;
    }
}
