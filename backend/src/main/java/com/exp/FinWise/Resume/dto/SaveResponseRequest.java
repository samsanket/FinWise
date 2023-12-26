package com.exp.FinWise.Resume.dto;


import org.springframework.web.multipart.MultipartFile;


public class SaveResponseRequest {
        private String title;
        private MultipartFile file;

        private Long requirnmentId;

    public Long getRequirnmentId() {
        return requirnmentId;
    }

    public void setRequirnmentId(Long requirnmentId) {
        this.requirnmentId = requirnmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "CreateResumeRequest{" +
                "title='" + title + '\'' +
                ", file=" + file +
                '}';
    }
}

