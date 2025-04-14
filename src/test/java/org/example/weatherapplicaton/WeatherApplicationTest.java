package org.example.weatherapplicaton;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class WeatherApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePageLoads() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(containsString("Weather Forecast")));
    }

    @Test
    public void testValidCityWeather() throws Exception {
        mockMvc.perform(get("/weather").param("city", "London"))
                .andExpect(status().isOk())
                .andExpect(view().name("weather"))
                .andExpect(model().attributeExists("cityName"))
                .andExpect(model().attributeExists("temperature"))
                .andExpect(model().attributeExists("description"))
                .andExpect(model().attribute("cityName", equalTo("London")))
                .andExpect(content().string(containsString("Temperature")))
                .andExpect(content().string(containsString("Humidity")));
    }

    @Test
    public void testInvalidCityHandling() throws Exception {
        mockMvc.perform(get("/weather").param("city", "InvalidCityName"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(content().string(containsString("City not found")));
    }
}



