package com.JPA.Board.Entity;

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
}
