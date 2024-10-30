package com.JPA.Board.DTO;

import java.time.LocalDateTime;

import com.JPA.Board.Entity.CommentEntity;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private Long boardId;

    private String commentWriter;
    private String commentContents;

    private LocalDateTime commentSavedTime;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long boardId) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentSavedTime(commentDTO.getCommentSavedTime());
        commentDTO.setBoardId(commentDTO.getBoardId());

        return commentDTO;
    }
}