package my.hydra.practice.repository;

import my.hydra.practice.domains.BoardDetail;
import my.hydra.practice.models.board.SelectBoardList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardDetailRepository extends JpaRepository<BoardDetail, Long> {
    @Query(value = "select new my.hydra.practice.models.board.SelectBoardList" +
            "(b.Title, m.memberId, b.ViewCount, b.CreateDate, b.UpdateDate) From BoardDetail b join b.member m where b.BoardNo = :boardNo",
    countQuery = "select count(b.No) From BoardDetail b join b.member m Where b.BoardNo =:boardNo")
    Page<SelectBoardList> getBoardDetailList(int boardNo, Pageable pageable);
}
