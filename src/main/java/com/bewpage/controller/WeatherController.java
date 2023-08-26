package com.bewpage.controller;

import com.bewpage.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {

  @Autowired private WeatherService weatherService;

  @GetMapping(value = "/byCity/{city}")
  public String getWeatherByCity(@PathVariable String city) {
    return weatherService.fetchWeather(city);
  }
}
