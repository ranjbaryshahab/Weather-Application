package com.shahab.weather.controller;

import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.shahab.weather.service.DateTime;
import com.shahab.weather.service.OpenWeatherMapApi.ApiController;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;
@SuppressWarnings({"Duplicates","unchecked","deprecation"})
public class UiController implements Initializable {

    @FXML
    private Label lblCity;

    @FXML
    private ImageView iconWeather;

    @FXML
    private Label lblAirTemperature;

    @FXML
    private Label lblWeatherDay;

    @FXML
    private Label lblDay;

    @FXML
    private Label lblDay1;

    @FXML
    private ImageView iconWeatherDay1;

    @FXML
    private Label lblDayAirTemperature1;

    @FXML
    private Label lblNightAirTemperature1;

    @FXML
    private Label lblWeatherDay1;

    @FXML
    private Label lblDay2;

    @FXML
    private ImageView iconWeatherDay2;

    @FXML
    private Label lblDayAirTemperature2;

    @FXML
    private Label lblNightAirTemperature2;

    @FXML
    private Label lblWeatherDay2;

    @FXML
    private Label lblDay3;

    @FXML
    private ImageView iconWeatherDay3;

    @FXML
    private Label lblDayAirTemperature3;

    @FXML
    private Label lblNightAirTemperature3;

    @FXML
    private Label lblWeatherDay3;

    @FXML
    private Label lblDay4;

    @FXML
    private ImageView iconWeatherDay4;

    @FXML
    private Label lblDayAirTemperature4;

    @FXML
    private Label lblNightAirTemperature4;

    @FXML
    private Label lblWeatherDay4;

    @FXML
    private Label lblDay5;

    @FXML
    private ImageView iconWeatherDay5;

    @FXML
    private Label lblDayAirTemperature5;

    @FXML
    private Label lblNightAirTemperature5;

    @FXML
    private Label lblWeatherDay5;

    @FXML
    private LineChart<?, ?> chart;

    @FXML
    private JFXSpinner humidity;

    @FXML
    private ImageView windHead;

    @FXML
    private Label wind;

    @FXML
    private JFXTextField city;

    @FXML
    private Pane pnlDay1;

    @FXML
    private Pane pnlDay2;

    @FXML
    private Pane pnlDay3;

    @FXML
    private Pane pnlDay4;

    @FXML
    private Pane pnlDay5;

    private static ApiController apiController = new ApiController();
    private int hour = 0;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        apiController.getOpenWeather("https://api.openweathermap.org/data/2.5/forecast?q=Tehran&appid=477e6ee48ec496639b7e1f8900b5ed69&units=metric");
        setPointToChart();

        lblDay1.setText(DateTime.getDate(1));
        lblDay2.setText(DateTime.getDate(2));
        lblDay3.setText(DateTime.getDate(3));
        lblDay4.setText(DateTime.getDate(4));
        lblDay5.setText(DateTime.getDate(5));


        rotateImg(windHead);





    }

    @FXML
    public void submit() {
        if (!city.getText().isEmpty()) {

            chart.getData().clear();
            apiController.getOpenWeather("https://api.openweathermap.org/data/2.5/forecast?q="+city.getText()+"&appid=477e6ee48ec496639b7e1f8900b5ed69&units=metric");
            setPointToChart();
            rotateImg(windHead);

        }

    }

    private void rotateImg(ImageView rotatePic){
        RotateTransition rt = new RotateTransition(Duration.seconds(80), rotatePic);
        rt.setByAngle(9 * 360);
        rt.play();
    }

    public static ApiController getApiController() {
        return apiController;
    }

    private int getHour() {
        Date date = new Date();
        if (0 <= date.getHours() && date.getHours() < 3) {
            this.hour = 0;
        } else if (3 <= date.getHours() && date.getHours() < 6) {
            this.hour = 3;
        } else if (6 <= date.getHours() && date.getHours() < 9) {
            this.hour = 6;
        } else if (9 <= date.getHours() && date.getHours() < 12) {
            this.hour = 9;
        } else if (12 <= date.getHours() && date.getHours() < 15) {
            this.hour = 12;
        } else if (15 <= date.getHours() && date.getHours() < 18) {
            this.hour = 15;
        } else if (18 <= date.getHours() && date.getHours() < 21) {
            this.hour = 18;
        } else if (21 <= date.getHours() && date.getHours() < 23) {
            this.hour = 21;
        }
        return hour;
    }

    private void setPointToChart() {
        Date[] dates = DateTime.dates();
        XYChart.Series today = new XYChart.Series();
        XYChart.Series day1 = new XYChart.Series();
        XYChart.Series day2 = new XYChart.Series();
        XYChart.Series day3 = new XYChart.Series();
        XYChart.Series day4 = new XYChart.Series();
        XYChart.Series day5 = new XYChart.Series();

        for (int i = 0; i < dates.length; i++) {
            if (DateTime.getDayDate(dates[i]).equals(DateTime.getDay(0))) {
                if (dates[i].getHours() == getHour()) {
                    lblCity.setText(apiController.getCity().getName());
                    lblDay.setText(DateTime.getDate(0));
                    lblWeatherDay.setText(apiController.getGetWeather().getLists().getWeathers().get(i).getDescription());
                    lblAirTemperature.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTemp()) + "°C");
                    iconWeather.setImage(new Image("http://openweathermap.org/img/w/" + apiController.getGetWeather().getLists().getWeathers().get(i).getIcon() + ".png"));
                    iconWeather.setScaleX(1.5);
                    iconWeather.setScaleY(1.5);
                    wind.setText(apiController.getGetWeather().getLists().getWinds().get(i).getSpeed() + " m/s");
                    humidity.setProgress(apiController.getGetWeather().getLists().getMainWeathers().get(i).getHumidity() / 100);
                }


                today.getData().addAll(new XYChart.Data(Integer.toString(dates[i].getHours()), (int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTemp())));

            }
            if (DateTime.getDayDate(dates[i]).equals(DateTime.getDay(1))) {
                if (dates[i].getHours() == getHour()) {
                    lblWeatherDay1.setText(apiController.getGetWeather().getLists().getWeathers().get(i).getDescription());
                    lblDayAirTemperature1.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTempMax()) + "°");
                    lblNightAirTemperature1.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTempMin()) + "°");
                    iconWeatherDay1.setImage(new Image("http://openweathermap.org/img/w/" + apiController.getGetWeather().getLists().getWeathers().get(i).getIcon() + ".png"));
                }
                day1.getData().addAll(new XYChart.Data(Integer.toString(dates[i].getHours()), (int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTemp())));

            }
            if (DateTime.getDayDate(dates[i]).equals(DateTime.getDay(2))) {
                if (dates[i].getHours() == getHour()) {
                    lblWeatherDay2.setText(apiController.getGetWeather().getLists().getWeathers().get(i).getDescription());
                    lblDayAirTemperature2.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTempMax()) + "°");
                    lblNightAirTemperature2.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTempMin()) + "°");
                    iconWeatherDay2.setImage(new Image("http://openweathermap.org/img/w/" + apiController.getGetWeather().getLists().getWeathers().get(i).getIcon() + ".png"));
                }
                day2.getData().addAll(new XYChart.Data(Integer.toString(dates[i].getHours()), (int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTemp())));

            }

            if (DateTime.getDayDate(dates[i]).equals(DateTime.getDay(3))) {

                if (dates[i].getHours() == getHour()) {
                    lblWeatherDay3.setText(apiController.getGetWeather().getLists().getWeathers().get(i).getDescription());
                    lblDayAirTemperature3.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTempMax()) + "°");
                    lblNightAirTemperature3.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTempMin()) + "°");
                    iconWeatherDay3.setImage(new Image("http://openweathermap.org/img/w/" + apiController.getGetWeather().getLists().getWeathers().get(i).getIcon() + ".png"));
                }
                day3.getData().addAll(new XYChart.Data(Integer.toString(dates[i].getHours()), (int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTemp())));

            }

            if (DateTime.getDayDate(dates[i]).equals(DateTime.getDay(4))) {
                if (dates[i].getHours() == getHour()) {
                    lblWeatherDay4.setText(apiController.getGetWeather().getLists().getWeathers().get(i).getDescription());
                    lblDayAirTemperature4.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTempMax()) + "°");
                    lblNightAirTemperature4.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTempMin()) + "°");
                    iconWeatherDay4.setImage(new Image("http://openweathermap.org/img/w/" + apiController.getGetWeather().getLists().getWeathers().get(i).getIcon() + ".png"));
                }
                day4.getData().addAll(new XYChart.Data(Integer.toString(dates[i].getHours()), (int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTemp())));

            }

            if (DateTime.getDayDate(dates[i]).equals(DateTime.getDay(5))) {
                lblWeatherDay5.setText(apiController.getGetWeather().getLists().getWeathers().get(i).getDescription());
                lblDayAirTemperature5.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTempMax()) + "°");
                lblNightAirTemperature5.setText((int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTempMin()) + "°");
                iconWeatherDay5.setImage(new Image("http://openweathermap.org/img/w/" + apiController.getGetWeather().getLists().getWeathers().get(i).getIcon() + ".png"));
                day5.getData().addAll(new XYChart.Data(Integer.toString(dates[i].getHours()), (int) Math.ceil(apiController.getGetWeather().getLists().getMainWeathers().get(i).getTemp())));

            }
        }
        chart.getData().addAll(today);

        pnlDay1.setOnMouseClicked(mouseEvent -> {
            chart.getData().clear();
            chart.getData().addAll(day1);
        });
        pnlDay2.setOnMouseClicked(mouseEvent -> {
            chart.getData().clear();
            chart.getData().addAll(day2);
        });
        pnlDay3.setOnMouseClicked(mouseEvent -> {
            chart.getData().clear();
            chart.getData().addAll(day3);
        });
        pnlDay4.setOnMouseClicked(mouseEvent -> {
            chart.getData().clear();
            chart.getData().addAll(day4);
        });
        pnlDay5.setOnMouseClicked(mouseEvent -> {
            chart.getData().clear();
            chart.getData().addAll(day5);
        });
    }

}