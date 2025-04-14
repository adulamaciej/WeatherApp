package org.example.weatherapplicaton.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {

    private String name;

    private Map<String, Object> main;

    private List<Map<String, Object>> weather;

    private Map<String, Object> wind;
}

