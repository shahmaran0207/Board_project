package com.JPA.Board.Repository;

import com.JPA.Board.DTO.BoardDTO;
import com.JPA.Board.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Modifying(clearAutomatically = true)
    @Query("update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id = :id")
    void updateHits(@Param("id") Long id);

}