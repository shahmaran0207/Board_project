package com.JPA.Board.Entity;

import com.JPA.Board.DTO.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Entity: DB의 테이블 역할을 하는 클래스

@Entity
@Getter
@Setter
@Table(name="boarad_table")
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

    public static BoardEntity toSaveEntity(BoardDTO boardDTo){
        BoardEntity boardEntity=new BoardEntity();

        boardEntity.setBoardWriter(boardDTo.getBoardWriter());
        boardEntity.setBoardTitle(boardDTo.getBoardTitle());
        boardEntity.setBoardPass(boardDTo.getBoardPass());
        boardEntity.setBoardContents(boardDTo.getBoardContents());
        boardEntity.setBoardHits(0);

        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity=new BoardEntity();

        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());

        return boardEntity;
    }
}
