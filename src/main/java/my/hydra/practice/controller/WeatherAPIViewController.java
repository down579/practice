package my.hydra.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherAPIViewController {
    @GetMapping(value="/weather/info")
    public String goWeatherInfo() {
        return "weatherinfo";
    }
}
