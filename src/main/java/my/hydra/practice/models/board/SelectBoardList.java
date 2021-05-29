package my.hydra.practice.models.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SelectBoardList {
    private long no;
    private int boardNo;
    private String title;
    private String memberId;
    private int viewCount;
    private LocalDateTime CreateDate;
    private LocalDateTime UpdateDate;
    private String content;

    public SelectBoardList(String title, String memberId, int viewCount,
                                      LocalDateTime createDate, LocalDateTime updateDate, long no, int boardNo, String content) {
        this.title = title;
        this.memberId = memberId;
        this.viewCount = viewCount;
        this.CreateDate = createDate;
        this.UpdateDate = updateDate;
        this.no = no;
        this.boardNo = boardNo;
        this.content = content;
    }
}
