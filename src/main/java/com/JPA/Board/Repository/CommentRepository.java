package com.JPA.Board.Repository;

import com.JPA.Board.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.JPA.Board.Entity.CommentEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findALlByEntityOrderByIdDESC(BoardEntity boardEntity);
}
