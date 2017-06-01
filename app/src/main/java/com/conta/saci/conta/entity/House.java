package com.conta.saci.conta.entity;

import java.io.Serializable;

/**
 * Created by JZLA on 30/05/2017.
 */

public class House implements Serializable {

    private Long id;

    private String houseName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
