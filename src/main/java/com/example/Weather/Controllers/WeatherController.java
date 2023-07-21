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



    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) throws JSONException {
            APIConnection(city);

            model.addAttribute("temperature", fromKelvinToCels(takingAPIForTemp(city)));
            model.addAttribute("feelsLike", fromKelvinToCels(takingAPIForFeels(city)));
            model.addAttribute("pressure", fromPhaToMm(takingAPIForPressure(city))+ " мм.рт.ст");
            model.addAttribute("city",city);

        return "weather";

    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }


}
