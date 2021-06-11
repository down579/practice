package my.hydra.practice.models.response;

import lombok.Getter;
import lombok.Setter;
import my.hydra.practice.models.ResponseCommon;
import my.hydra.practice.models.board.SelectBoardList;
import org.springframework.data.domain.Page;


@Getter
@Setter
public class ResponseGetBoardDetailList {
    private ResponseCommon result;
    private Page<SelectBoardList> dataList;
}
