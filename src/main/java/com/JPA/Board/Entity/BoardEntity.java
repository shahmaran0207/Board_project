package com.JPA.Board.Entity;

import com.JPA.Board.DTO.BoardDTO;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

//Entity: DB의 테이블 역할을 하는 클래스

@Entity
@Getter
@Setter
@Table(name="board_table")
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)  //크기 20, not null 지정
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;

    @Column
    private String boardContents;

    @Column
    private int boardHits;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static BoardEntity toSaveEntity(BoardDTO boardDTo){
        BoardEntity boardEntity=new BoardEntity();

        boardEntity.setBoardContents(boardDTo.getBoardContents());
        boardEntity.setBoardWriter(boardDTo.getBoardWriter());
        boardEntity.setBoardTitle(boardDTo.getBoardTitle());
        boardEntity.setBoardPass(boardDTo.getBoardPass());
        boardEntity.setFileAttached(0);
        boardEntity.setBoardHits(0);

        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity=new BoardEntity();

        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        boardEntity.setId(boardDTO.getId());

        return boardEntity;
    }

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity=new BoardEntity();

        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setFileAttached(0);
        boardEntity.setBoardHits(1);

        return boardEntity;
    }
}