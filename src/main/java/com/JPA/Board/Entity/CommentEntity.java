package com.JPA.Board.Entity;

import com.JPA.Board.DTO.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="comment_table")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String commentWriter;

    @Column
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private BoardEntity boardEntity;

    public static CommentEntity toSaveEntity(CommentDTO comment, BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(comment.getCommentWriter());
        commentEntity.setCommentContents(comment.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);

        return commentEntity;
    }
}