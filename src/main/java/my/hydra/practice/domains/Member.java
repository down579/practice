package my.hydra.practice.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_Member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="membercode")
    private long memberCode;

    @Column(name="memberid", unique = true, nullable = false)
    private String memberId;

    @Column(name="membername", nullable = false)
    private String memberName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name ="createdate",nullable = false)
    private LocalDateTime createDate;

    public Member(String memberId, String memberName, String password) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCreateDate(LocalDateTime datetime) {
        this.createDate = datetime;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<BoardDetail> boardDetails = new ArrayList<BoardDetail>();
}
