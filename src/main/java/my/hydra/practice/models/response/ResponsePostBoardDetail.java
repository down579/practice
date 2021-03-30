package my.hydra.practice.models.response;

import lombok.Getter;
import lombok.Setter;
import my.hydra.practice.models.ResponseCommon;

@Setter
@Getter
public class ResponsePostBoardDetail {
    private ResponseCommon result;
    private long BoardNo;
}
