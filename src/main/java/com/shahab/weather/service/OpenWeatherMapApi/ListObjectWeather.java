package com.shahab.weather.service.OpenWeatherMapApi;

import java.util.ArrayList;

public class ListObjectWeather {
    private ArrayList<Weather> weathers;
    private ArrayList<Wind> winds;
    private ArrayList<Sys> sys;
    private ArrayList<MainWeather> mainWeathers;
    private ArrayList<Clouds> clouds;
    private ArrayList<Long> dt;
    private ArrayList<String> dtText;

    public ArrayList<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(ArrayList<Weather> weathers) {
        this.weathers = weathers;
    }

    public ArrayList<Wind> getWinds() {
        return winds;
    }

    public void setWinds(ArrayList<Wind> winds) {
        this.winds = winds;
    }

    public ArrayList<Sys> getSys() {
        return sys;
    }

    public void setSys(ArrayList<Sys> sys) {
        this.sys = sys;
    }

    public ArrayList<MainWeather> getMainWeathers() {
        return mainWeathers;
    }

    public void setMainWeathers(ArrayList<MainWeather> mainWeathers) {
        this.mainWeathers = mainWeathers;
    }

    public ArrayList<Clouds> getClouds() {
        return clouds;
    }

    public void setClouds(ArrayList<Clouds> clouds) {
        this.clouds = clouds;
    }

    public ArrayList<Long> getDt() {
        return dt;
    }

    public void setDt(ArrayList<Long> dt) {
        this.dt = dt;
    }

    public ArrayList<String> getDtText() {
        return dtText;
    }

    public void setDtText(ArrayList<String> dtText) {
        this.dtText = dtText;
    }
}
