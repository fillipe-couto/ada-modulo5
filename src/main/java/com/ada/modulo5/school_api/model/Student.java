package com.ada.modulo5.school_api.model;

import com.ada.modulo5.school_api.enums.Gender;

public class Student {

    private int id;
    private int registerCode;
    private String name;
    private Gender gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(int register) {
        this.registerCode = register;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
