package my.hydra.practice.repository;

import my.hydra.practice.domains.LocalCode;
import my.hydra.practice.models.LocalCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalCodeRepository extends JpaRepository<LocalCode, Integer> {
    @Query("Select l.level1 From LocalCode l group by l.level1")
    public List<String> GroupByLevel1();

    @Query("Select l.level2 From LocalCode l Where l.level1 = :level1 and l.level2 <> '' group by l.level2")
    public List<String> GroupByLevel2(String level1);

    @Query("Select new my.hydra.practice.models.LocalCoordinate(l.level3,l.x,l.y) From LocalCode l Where l.level1 = :level1 and l.level2 = :level2" +
            " and l.level3 <> '' group by l.level3,l.x,l.y")
    public List<LocalCoordinate> GroupBylevel3(String level1, String level2);
}
