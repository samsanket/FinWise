package com.exp.FinWise.Resume.model;

public class FileInfo {
  private String name;
  private String url;

  private ResumeData resumeData;



  public FileInfo(String name, String url) {
    this.name = name;
    this.url = url;
  }
  public ResumeData getResumeData() {
    return resumeData;
  }

  public void setResumeData(ResumeData resumeData) {
    this.resumeData = resumeData;
  }
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
