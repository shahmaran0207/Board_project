package com.JPA.Board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.JPA.Board.Entity.CommentEntity;
import com.JPA.Board.Entity.BoardEntity;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}