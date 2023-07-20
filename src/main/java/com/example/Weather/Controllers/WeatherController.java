package com.example.Weather.Controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import static com.example.Weather.TakingWeatherFromAPI.TakingWeather.*;

@Controller
public class WeatherController {

    private static final String API = "&type=like&APPID=97873f87fc1b4927356aa0b926698655";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) throws JSONException {
        String output = getUrlContent("http://api.openweathermap.org/data/2.5/find?q=" + city + "&type=like&APPID=97873f87fc1b4927356aa0b926698655");
        System.out.println(output);

        JSONObject obj = new JSONObject(output);
        JSONArray list = obj.getJSONArray("list");

        for (int i = 0; i < list.length(); i++) {
            JSONObject cityObj = list.getJSONObject(i);
            JSONObject mainObj = cityObj.getJSONObject("main");


            int  temperature = (int) mainObj.getDouble("temp");
            int feelsLike = (int) mainObj.getDouble("feels_like");
            int pressure =  mainObj.getInt("pressure");



            model.addAttribute("temperature", fromKelvinToCels(temperature));
            model.addAttribute("feelsLike", fromKelvinToCels(feelsLike));
            model.addAttribute("pressure", fromPhaToMm(pressure)+ " мм.рт.ст");
            model.addAttribute("city",city);
        }

        return "weather";

    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }


}
