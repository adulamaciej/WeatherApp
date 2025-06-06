package org.example.weatherapplicaton.service;


import lombok.RequiredArgsConstructor;
import org.example.weatherapplicaton.data.WeatherData;
import org.example.weatherapplicaton.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.time.Duration;


@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WebClient webClient;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public WeatherData getWeather(String city) {
        String url = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";

        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new CityNotFoundException("City not found: " + city)))
                .bodyToMono(WeatherData.class)
                .timeout(Duration.ofSeconds(10))
                .retry(2)
                .block();
    }

}
