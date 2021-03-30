package my.hydra.practice.repository;

import my.hydra.practice.domains.BoardCode;
import my.hydra.practice.models.board.SelectBoardNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardCodeRepository extends JpaRepository<BoardCode, Integer> {
    @Query("Select new my.hydra.practice.models.board.SelectBoardNo(a.BoardNo) From BoardCode a Where a.BoardNo = :boardNumber")
    SelectBoardNo findBoardNoByBoardNo(int boardNumber);
}
