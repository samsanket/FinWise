package com.exp.FinWise.Resume.model;

import jakarta.persistence.*;

@Table(name = "it_keyword")
@Entity
public class ITKeywordsSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long skillId;

    @Column(name = "skills")
    private String skills;

    @Column(name = "status")
    private String status;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ITSkillsSet{" +
                "itSkillsId=" + skillId +
                ", skills='" + skills + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
