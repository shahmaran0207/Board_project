package com.JPA.Board.DTO;

import lombok.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class BoardDTO {

    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    private int boardHits;
}
