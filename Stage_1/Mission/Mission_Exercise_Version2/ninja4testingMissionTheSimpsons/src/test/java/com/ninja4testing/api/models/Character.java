package com.ninja4testing.api.models;

import java.util.List;

public class Character {

    private Integer id;
    private Integer age;
    private String birthdate;
    private String gender;
    private String name;
    private String occupation;
    private String portrait_path;
    private List<String> phrases;
    private String status;


    public Character(Integer id, Integer age, String birthdate, String gender, String name, String occupation, String portrait_path, List<String> phrases, String status ) {
        this.id = id;
        this.age = age;
        this.birthdate = birthdate;
        this.gender = gender;
        this.name = name;
        this.occupation = occupation;
        this.portrait_path = portrait_path;
        this.phrases = phrases;
        this.status = status;
    }

    public Character() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() { return gender; }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPortrait_path() { return portrait_path; }
    public void setPortrait_path(String portrait_path) {
        this.portrait_path = portrait_path;
    }

    public List<String> getPhrases() { return phrases; }
    public void setPhrases(List<String> phrases) {
        this.phrases = phrases;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = status;
    }
}
