package com.conta.saci.conta.entity;

import java.io.Serializable;

/**
 * Created by JZLA on 30/05/2017.
 */

public class Person implements Serializable {

    private Long id;

    private String name;

    private Gender gender;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return name;
    }
}
