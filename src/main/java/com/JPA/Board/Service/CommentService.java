package com.JPA.Board.Service;

import com.JPA.Board.Repository.CommentRepository;
import com.JPA.Board.Repository.BoardRepository;
import org.springframework.stereotype.Service;
import com.JPA.Board.Entity.CommentEntity;
import com.JPA.Board.Entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import com.JPA.Board.DTO.CommentDTO;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO comment) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(comment.getBoardId());

        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(comment, boardEntity);
            return commentRepository.save(commentEntity).getId();
        } else{
            return null;
        }
    }

    public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findALlByEntityOrderByIdDESC(boardEntity);
        // EntityList -> DTOList

        List<CommentDTO> commentDTOList = new ArrayList<>();

        for(CommentEntity commentEntity : commentEntityList) {
            CommentDTO commentdto = CommentDTO.toCommentDTO(commentEntity);
            commentDTOList.add(commentdto);
        }
        return commentDTOList;
    }
}