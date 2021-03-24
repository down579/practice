package my.hydra.practice.repository;

import my.hydra.practice.domains.BoardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDetailRepository extends JpaRepository<BoardDetail, Long> {
}
