package org.example.weatherapplicaton.service;


import lombok.RequiredArgsConstructor;
import org.example.weatherapplicaton.data.WeatherData;
import org.example.weatherapplicaton.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;


@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestClient restClient;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public WeatherData getWeather(String city) {
        String url = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";
        try {
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(WeatherData.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException("City not found: " + city);
        }
    }
}
