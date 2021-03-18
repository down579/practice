package my.hydra.practice.controller;

import lombok.RequiredArgsConstructor;
import my.hydra.practice.service.LocalCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocalCodeAPIController {
    private final LocalCodeService localCodeService;

    @GetMapping(value = "/local-code/level1")
    public List<String> getLevel1Code() {
        return localCodeService.getLevel1Code();
    }

    @GetMapping(value = "/local-code/level1/{level1}/level2")
    public List<String> getLevel2Code(@PathVariable(name="level1") String level1) {
        return localCodeService.getLevel2Code(level1);
    }

    @GetMapping(value = "/local-code/level1/{level1}/level2/{level2}/level3")
    public List<String> getLevel3Code(@PathVariable(name="level1") String level1,
                                      @PathVariable(name="level2") String level2) {
        return localCodeService.getLevel3Code(level1, level2);
    }
}
