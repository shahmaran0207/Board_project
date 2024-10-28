package com.JPA.Board.Service;

import com.JPA.Board.DTO.BoardDTO;
import com.JPA.Board.Entity.BoardEntity;
import com.JPA.Board.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//DTO -> Entity (EntityClass)
//Entity -> DTO (DTOClass)

//Service 클래스에서 반드시 DTO를 Entity로 변환 해야함

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);  // 저장
    }
}