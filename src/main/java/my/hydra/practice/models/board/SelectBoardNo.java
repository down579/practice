package my.hydra.practice.models.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectBoardNo {
    private int BoardNo;
    public SelectBoardNo(int BoardNo) {
        this.BoardNo = BoardNo;
    }
}
