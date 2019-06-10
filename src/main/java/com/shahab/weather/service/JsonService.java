package com.shahab.weather.service;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * convert string to json object or json array
 */
public class JsonService {

    public JSONObject setStringToJson(String text){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject= null;
        try {
             jsonObject = (JSONObject) jsonParser.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public JSONArray getJsonArray(JSONObject jsonObject,String get){
        return (JSONArray) jsonObject.get(get);
    }

    public JSONObject getObjectAsArray(JSONObject jsonObject ,String get){
        JSONObject jsonObject1 = null;
        try {
            String result = jsonObject.get(get).toString();
            JSONParser jsonParser = new JSONParser();
            jsonObject1 = (JSONObject) jsonParser.parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject1;
    }



}