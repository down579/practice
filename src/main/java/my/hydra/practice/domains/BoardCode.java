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
    @Column(name="BoardNo")
    public int BoardNo;

    @Column(name = "BoardTitle", nullable = false)
    public String BoardTitle;

    @Column(name = "CreateDate", nullable = false)
    public LocalDateTime CreateDate;

    @Column(name = "UpdateDate", nullable = false)
    public LocalDateTime UpdateDate;

    @Column(name="CreateMember", nullable = false)
    public int CreateMember;

    @Column(name="UpdateMember", nullable = false)
    public int UpdateMember;

    @Column(name = "Remark", nullable = false)
    public String Remark;


    public void setCreateDate(LocalDateTime date) {
        this.CreateDate = date;
    }

    public void setUpdateDate(LocalDateTime date) {
        this.UpdateDate = date;
    }

}
