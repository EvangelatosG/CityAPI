package com.example.cityAPI.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name = "apicall")
public class ApiCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private City city;

    public ApiCall(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
