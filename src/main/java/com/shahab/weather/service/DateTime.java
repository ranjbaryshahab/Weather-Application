package com.shahab.weather.service;

import com.shahab.weather.controller.UiController;
import com.shahab.weather.service.OpenWeatherMapApi.ApiController;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTime {
    public static Date[] dates(){
        ApiController apiController = UiController.getApiController();
        Date[] d = new Date[apiController.getGetWeather().getLists().getDtText().size()];
        for(int i =0;i<apiController.getGetWeather().getLists().getDtText().size();i++){
            String dt =apiController.getGetWeather().getLists().getDtText().get(i);
            SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                d[i]=formatter6.parse(dt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return d ;
    }

    public static String getDate(int number){
        Date f = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(f);
        c.add(Calendar.DATE, number);
        f = c.getTime();
        Format dateFormat = new SimpleDateFormat("EEEEE, dd");
        return dateFormat.format(f);
    }

    public static String getDay(int number){
        Date f = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(f);
        c.add(Calendar.DATE, number);
        f = c.getTime();
        Format dateFormat = new SimpleDateFormat("d");
        return dateFormat.format(f);
    }

    public static String getDayDate(Date f){
        Calendar c = new GregorianCalendar();
        Format dateFormat = new SimpleDateFormat("d");
        return dateFormat.format(f);
    }

}
