package com.JPA.Board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.JPA.Board.Entity.BoardFileEntity;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
}
