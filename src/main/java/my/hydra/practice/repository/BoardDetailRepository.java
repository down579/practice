package my.hydra.practice.repository;

import my.hydra.practice.domains.BoardDetail;
import my.hydra.practice.models.board.SelectBoardList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

public interface BoardDetailRepository extends JpaRepository<BoardDetail, Long> {
    @Query(value = "select new my.hydra.practice.models.board.SelectBoardList" +
            "(b.Title, m.memberId, b.ViewCount, b.CreateDate, b.UpdateDate, b.No, b.BoardNo, b.Content) From BoardDetail b join b.member m where b.BoardNo = :boardNo",
    countQuery = "select count(b.No) From BoardDetail b join b.member m Where b.BoardNo =:boardNo")
    Page<SelectBoardList> getBoardDetailList(int boardNo, Pageable pageable);

    @Query(value = "select new my.hydra.practice.models.board.SelectBoardList" +
    "(b.Title, m.memberId, b.ViewCount, b.CreateDate, b.UpdateDate, b.No, b.BoardNo, b.Content) From BoardDetail b join b.member m where b.No = :no")
    SelectBoardList getBoardDetailByNo(long no);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update BoardDetail b Set b.ViewCount = b.ViewCount + 1 where b.No =:no")
    void updateBoardViewCount(long no);

    @Modifying
    @Transactional
    @Query(value = "delete from BoardDetail b where b.BoardNo =:boardNo and b.No =:no")
    int deleteBoard(int boardNo, long no);

    @Modifying
    @Transactional
    @Query(value = "update BoardDetail b Set b.Title = :title, b.Content = :content, b.UpdateDate =:updateDate" +
            " where b.BoardNo = :boardNo and b.No =:no")
    int updateBoard(String title, String content, LocalDateTime updateDate, int boardNo, long no);
}
