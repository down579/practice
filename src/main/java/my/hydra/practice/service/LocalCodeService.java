package my.hydra.practice.service;

import lombok.RequiredArgsConstructor;
import my.hydra.practice.repository.LocalCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalCodeService {
    private final LocalCodeRepository localCodeRepository;

    public List<String> getLevel1Code() {
        return localCodeRepository.GroupByLevel1();
    }

    public List<String> getLevel2Code(String level1) {
        return localCodeRepository.GroupByLevel2(level1);
    }

    public List<String> getLevel3Code(String level1, String level2) {
        return localCodeRepository.GroupBylevel3(level1, level2);
    }
}
