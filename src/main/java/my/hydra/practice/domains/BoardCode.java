package my.hydra.practice.domains;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tbl_Board_Code")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="boardno")
    public int BoardNo;

    @Column(name = "boardtitle", nullable = false)
    public String BoardTitle;

    @Column(name = "createdate", nullable = false)
    public LocalDateTime CreateDate;

    @Column(name = "updatedate", nullable = false)
    public LocalDateTime UpdateDate;

    @Column(name="createmember", nullable = false)
    public int CreateMember;

    @Column(name="updatemember", nullable = false)
    public int UpdateMember;

    @Column(name = "remark", nullable = false)
    public String Remark;


    public void setCreateDate(LocalDateTime date) {
        this.CreateDate = date;
    }

    public void setUpdateDate(LocalDateTime date) {
        this.UpdateDate = date;
    }

}
