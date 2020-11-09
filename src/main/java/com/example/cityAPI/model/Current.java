package com.example.cityAPI.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Current {

    @Embedded
    private Weather weather;

    @Embedded
    private Pollution pollution;

    public Current(){

    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Pollution getPollution() {
        return pollution;
    }

    public void setPollution(Pollution pollution) {
        this.pollution = pollution;
    }
}
