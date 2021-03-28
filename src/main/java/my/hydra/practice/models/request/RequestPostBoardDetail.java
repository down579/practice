package my.hydra.practice.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPostBoardDetail {
    private String title;
    private String content;
    private int boardNo;
}
