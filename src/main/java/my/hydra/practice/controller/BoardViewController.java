package my.hydra.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class BoardViewController {
    @RequestMapping(value = "/board/{boardNo}/regist", method = RequestMethod.GET)
    public String showRegistBoard(@PathVariable int boardNo, Map<String, Object> model) {
        model.put("boardNo",boardNo);
        return "/board/boardRegist";
    }
    @RequestMapping(value="/board/{boardNo}/list", method = RequestMethod.GET)
    public String showListBoard(@PathVariable int boardNo, Map<String, Object> model) {
        model.put("boardNo",boardNo);
        return "/board/boardList";
    }

}
