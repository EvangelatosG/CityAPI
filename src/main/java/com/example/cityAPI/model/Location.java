package com.example.cityAPI.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Embeddable
public class Location {

    private String type;

    @ElementCollection
    @CollectionTable(name = "city_coordinates", joinColumns = @JoinColumn(name = "apicall_id"))
    @Column(name = "coordinate")
    private List<Double> coordinates = new ArrayList<>();

    public Location(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

}
