package my.hydra.practice.models.response;

import lombok.Setter;
import my.hydra.practice.models.ResponseCommon;

@Setter
public class ResponsePostBoardDetail {
    private ResponseCommon result;
    private long BoardNo;
}
