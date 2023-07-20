package com.example.Weather.TakingWeatherFromAPI;

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

    public static int fromKelvinToCels(int temperature) {
        return temperature - 273;
    }

    public static double fromPhaToMm(int pressure) {
        return (int)(pressure * 0.75006375541921);
    }



}
