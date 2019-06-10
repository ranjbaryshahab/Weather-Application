package com.shahab.weather.service.OpenWeatherMapApi;

public class getWeather {
    private City city;
    private ListObjectWeather lists;
    private String cod;
    private double message;
    private int cnt;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ListObjectWeather getLists() {
        return lists;
    }

    public void setLists(ListObjectWeather lists) {
        this.lists = lists;
    }

    public  String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
