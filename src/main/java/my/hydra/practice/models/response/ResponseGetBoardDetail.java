package my.hydra.practice.models.response;

import lombok.Getter;
import lombok.Setter;
import my.hydra.practice.models.ResponseCommon;
import my.hydra.practice.models.board.SelectBoardList;

@Getter
@Setter
public class ResponseGetBoardDetail extends ResponseCommon {
    private SelectBoardList detail;
    private boolean isRegistMember;
}
