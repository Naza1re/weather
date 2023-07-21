package com.example.Weather.TakingWeatherFromAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TakingWeather {

    public static String getUrlContent(String urlAddress) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static String APIConnection(String city) throws JSONException {
         return getUrlContent("http://api.openweathermap.org/data/2.5/find?q=" + city + "&type=like&APPID=97873f87fc1b4927356aa0b926698655");
    }

    public static int takingAPIForTemp(String city) throws JSONException {
        JSONObject obj = new JSONObject(APIConnection(city));
        JSONArray list = obj.getJSONArray("list");
        JSONObject mainObj = null;

        for (int i = 0; i < list.length(); i++) {
            JSONObject cityObj = list.getJSONObject(i);
             mainObj = cityObj.getJSONObject("main");

        }

        return (int) mainObj.getDouble("temp");

    }

    public static int takingAPIForFeels(String city) throws JSONException {
        JSONObject obj = new JSONObject(APIConnection(city));
        JSONArray list = obj.getJSONArray("list");
        JSONObject mainObj = null;

        for (int i = 0; i < list.length(); i++) {
            JSONObject cityObj = list.getJSONObject(i);
            mainObj = cityObj.getJSONObject("main");

        }

        return (int) mainObj.getDouble("feels_like");

    }

    public static int takingAPIForPressure(String city) throws JSONException {
        JSONObject obj = new JSONObject(APIConnection(city));
        JSONArray list = obj.getJSONArray("list");
        JSONObject mainObj = null;

        for (int i = 0; i < list.length(); i++) {
            JSONObject cityObj = list.getJSONObject(i);
            mainObj = cityObj.getJSONObject("main");

        }

        return (int) mainObj.getDouble("pressure");

    }
    public static int fromKelvinToCels(int temp){
        return temp - 273;
    }

    public static double fromPhaToMm(int pressure){
        return (int) (pressure * 0.75006375541921);
    }
}
