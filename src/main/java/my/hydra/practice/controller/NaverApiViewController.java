package my.hydra.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NaverApiViewController {

    @RequestMapping(method= RequestMethod.GET, value="/template")
    public String template() {
        return "practice-template";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/naver/news")
    public String test() {
        return "navernews";
    }
}
