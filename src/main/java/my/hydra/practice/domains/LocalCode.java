package my.hydra.practice.domains;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name="tbl_local_code")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int id;

    @Column(name="level1")
    public String level1;

    @Column(name="level2")
    public String level2;

    @Column(name="level3")
    public String level3;

    @Column(name="x")
    public int x;

    @Column(name="y")
    public int y;

    @Column(name="memo")
    public String memo;
}
