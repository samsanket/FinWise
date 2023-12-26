package com.exp.FinWise.audit.dto;

public class AuditDtoPy {
    String user;

    String apiName;

    String data;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        user = "python";
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
