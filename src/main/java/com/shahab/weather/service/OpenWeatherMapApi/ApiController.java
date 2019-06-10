package com.shahab.weather.service.OpenWeatherMapApi;

import com.shahab.weather.service.JsonService;
import com.shahab.weather.service.Url_Connection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * api controller class , get json api and set object class
 */
public class ApiController {
    private ListObjectWeather list;
    private City city;
    private getWeather getWeather;
    private Url_Connection connection;
    private JsonService jsonService;

    public ApiController() {
        list = new ListObjectWeather();
        city = new City();
        getWeather = new getWeather();
        connection = new Url_Connection();
        jsonService = new JsonService();

    }
//connection to url and read json
    public void getOpenWeather(String urlS) {
        JSONObject jsonObject;
        URL url;
        String result;
        try {
            url = new URL(urlS);
            connection.setUrl(url);
            if (connection.connection(url)) {
                result = connection.openStream(url);
                jsonObject = jsonService.setStringToJson(result);
                if (jsonObject != null) {
                    JSONObject cityObject = jsonService.getObjectAsArray(jsonObject, "city");
                    JSONObject coordObject = jsonService.getObjectAsArray(cityObject, "coord");

                    Coord coord = new Coord();
                    coord.setLon((Double) coordObject.get("lon"));
                    coord.setLat((Double) coordObject.get("lat"));
                    city.setCoord(coord);
                    city.setCountry(cityObject.get("country").toString());
                    city.setId((Long) cityObject.get("id"));
                    city.setName(cityObject.get("name").toString());
                    city.setPopulation((Long) cityObject.get("population"));
                    city.setTimeZone((Long) cityObject.get("timezone"));

                    JSONArray array = jsonService.getJsonArray(jsonObject,"list");
                    ArrayList<MainWeather> mainWeatherArrayList = new ArrayList<>();
                    ArrayList<Clouds> cloudsArrayList = new ArrayList<>();
                    ArrayList<Wind> windArrayList = new ArrayList<>();
                    ArrayList<Sys> sysArrayList = new ArrayList<>();
                    ArrayList<Weather> weatherArrayList = new ArrayList<>();
                    ArrayList<Long> dtArrayList = new ArrayList<>();
                    ArrayList<String> dtTextArrayList = new ArrayList<>();


                    String[] s = new String[array.size()];
                    for (int i=0;i<array.size();i++) {
                        JSONObject objectList = jsonService.setStringToJson(array.get(i).toString());

                        JSONObject mainObject = jsonService.getObjectAsArray(objectList,"main");
                        JSONObject cloudsObject = jsonService.getObjectAsArray(objectList,"clouds");
                        JSONObject windObject = jsonService.getObjectAsArray(objectList,"wind");
                        JSONObject sysObject = jsonService.getObjectAsArray(objectList,"sys");

                        MainWeather mainWeather = new MainWeather();
                        mainWeather.setTemp(Double.parseDouble(mainObject.get("temp").toString()));
                        mainWeather.setTempMin(Double.parseDouble(mainObject.get("temp_min").toString()));
                        mainWeather.setTempMax(Double.parseDouble(mainObject.get("temp_max").toString()));
                        mainWeather.setHumidity(Double.parseDouble(mainObject.get("humidity").toString()));
                        mainWeather.setPressure(Double.parseDouble(mainObject.get("pressure").toString()));
                        mainWeather.setGrndLevel(Double.parseDouble(mainObject.get("grnd_level").toString()));
                        mainWeather.setTempKf(Double.parseDouble(mainObject.get("temp_kf").toString()));
                        mainWeather.setSeaLevel(Double.parseDouble(mainObject.get("sea_level").toString()));
                        mainWeatherArrayList.add(mainWeather);

                        Clouds clouds = new Clouds();
                        clouds.setAll(Integer.parseInt(cloudsObject.get("all").toString()));
                        cloudsArrayList.add(clouds);

                        Wind wind = new Wind();
                        wind.setSpeed(Double.parseDouble(windObject.get("speed").toString()));
                        wind.setDeg(Double.parseDouble(windObject.get("deg").toString()));
                        windArrayList.add(wind);

                        Sys sys = new Sys();
                        sys.setPod(sysObject.get("pod").toString());
                        sysArrayList.add(sys);

                        JSONArray weatherArray = jsonService.getJsonArray(objectList,"weather");
                        for(int l=0;l<weatherArray.size();l++){
                            JSONObject weatherObject = jsonService.setStringToJson(weatherArray.get(l).toString());
                            Weather weather = new Weather();
                            weather.setId(Long.parseLong(weatherObject.get("id").toString()));
                            weather.setIcon(weatherObject.get("icon").toString());
                            weather.setDescription(weatherObject.get("description").toString());
                            weather.setMain(weatherObject.get("main").toString());
                            weatherArrayList.add(weather);
                        }

                        dtArrayList.add(Long.parseLong(objectList.get("dt").toString()));
                        dtTextArrayList.add(objectList.get("dt_txt").toString());


                    }




                    list.setMainWeathers(mainWeatherArrayList);
                    list.setClouds(cloudsArrayList);
                    list.setWinds(windArrayList);
                    list.setSys(sysArrayList);
                    list.setWeathers(weatherArrayList);
                    list.setDt(dtArrayList);
                    list.setDtText(dtTextArrayList);


                    getWeather.setLists(list);
                    getWeather.setCity(city);

                    getWeather.setCnt(Integer.parseInt(jsonObject.get("cnt").toString()));
                    getWeather.setCod(jsonObject.get("cod").toString());
                    getWeather.setMessage(Double.parseDouble(jsonObject.get("message").toString()));

                }
            }
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public ListObjectWeather getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    public com.shahab.weather.service.OpenWeatherMapApi.getWeather getGetWeather() {
        return getWeather;
    }
}
