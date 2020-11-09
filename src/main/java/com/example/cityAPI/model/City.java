package com.example.cityAPI.model;

import javax.persistence.*;

@Embeddable
public class City {

    private String status;

    @Embedded
    private Data data;

    public City(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
