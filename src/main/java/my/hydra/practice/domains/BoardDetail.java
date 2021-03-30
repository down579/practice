package my.hydra.practice.domains;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="tbl_board_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="no")
    private long No;

    @Column(name="boardno")
    private int BoardNo;

    @Column(name = "title")
    private String Title;

    @Column(name="content")
    private String Content;

    @Column(name="createdate")
    private LocalDateTime CreateDate;

    @Column(name="updatedate")
    private LocalDateTime UpdateDate;
    @JoinColumn(name="membercode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    public BoardDetail(int boardNo, String title, String content) {
        this.BoardNo = boardNo;
        this.Title = title;
        this.Content = content;
    }

    public void setCreateDate(LocalDateTime date) {
        this.CreateDate = date;
    }

    public void setUpdateDate(LocalDateTime date) {
        this.UpdateDate = date;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getBoardDetails().add(this);
    }


}
