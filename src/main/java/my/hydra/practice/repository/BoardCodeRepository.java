package my.hydra.practice.repository;

import my.hydra.practice.domains.BoardCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCodeRepository extends JpaRepository<BoardCode, Integer> {
    int findBoardCodeByBoardNo(int boardNo);
}
