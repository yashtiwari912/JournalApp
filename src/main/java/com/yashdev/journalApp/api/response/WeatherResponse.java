package com.yashdev.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class WeatherResponse {

    private ArrayList<Weather> weather;

    private Main main;

    private String name;

    @Getter
    @Setter
    public static class Main{
        private double temp;
        @JsonProperty("feels_like")
        private double feelsLike;
        private int humidity;
    }

    @Getter
    @Setter
    public static class Weather{
        private int id;
        private String main;
        private String description;
        private String icon;
    }

}
