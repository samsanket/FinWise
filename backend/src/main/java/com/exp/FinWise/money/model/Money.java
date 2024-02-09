package com.exp.FinWise.money.model;

import com.exp.FinWise.usersOnBoarding.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "money")
public class Money {

    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @Column(name = "ammount")
    String ammount;

    @Column(name = "reason")
    String reason;

    @Column(name = "location")
    String location;

    @Column(name = "dateTime" )
    LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Money{" +
                "id=" + id +
                ", ammount='" + ammount + '\'' +
                ", reason='" + reason + '\'' +
                ", location='" + location + '\'' +
                ", dateTime=" + dateTime +
                ", user=" + user +
                '}';
    }

    public Money() {
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

    public Money(Long id, String ammount, String reason, String location, LocalDateTime dateTime, User user) {
        this.id = id;
        this.ammount = ammount;
        this.reason = reason;
        this.location = location;
        this.dateTime = dateTime;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
