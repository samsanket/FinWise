package com.exp.FinWise.money.dto;

import java.time.LocalDateTime;

public class MoneyDto {


    Long id;
    String ammount;
    String reason;
    String location;
    LocalDateTime dateTime;


    public MoneyDto() {
    }

    public MoneyDto(Long id, String ammount, String reason, String location, LocalDateTime dateTime) {
        this.id = id;
        this.ammount = ammount;
        this.reason = reason;
        this.location = location;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
