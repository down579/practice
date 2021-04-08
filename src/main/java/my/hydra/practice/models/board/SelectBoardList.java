package my.hydra.practice.models.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SelectBoardList {
    public String title;
    public String memberId;
    public int viewCount;
    public LocalDateTime CreateDate;
    public LocalDateTime UpdateDate;

    public SelectBoardList(String title, String memberId, int viewCount,
                                      LocalDateTime createDate, LocalDateTime updateDate) {
        this.title = title;
        this.memberId = memberId;
        this.viewCount = viewCount;
        this.CreateDate = createDate;
        this.UpdateDate = updateDate;
    }
}
