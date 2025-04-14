package org.example.weatherapplicaton.controller;


import lombok.RequiredArgsConstructor;
import org.example.weatherapplicaton.data.WeatherData;
import org.example.weatherapplicaton.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather(
            @RequestParam(value = "city")
            String city,
            Model model
    ) {
        WeatherData weather = weatherService.getWeather(city);

        model.addAttribute("cityName", weather.getName());
        model.addAttribute("temperature", weather.getMain().get("temp"));
        model.addAttribute("description", weather.getWeather().getFirst().get("description"));
        model.addAttribute("humidity", weather.getMain().get("humidity"));
        model.addAttribute("windSpeed", weather.getWind().get("speed"));
        model.addAttribute("pressure", weather.getMain().get("pressure"));

        return "weather";
    }
}