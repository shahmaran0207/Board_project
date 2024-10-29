package com.JPA.Board.DTO;

import org.springframework.web.multipart.MultipartFile;
import com.JPA.Board.Entity.BoardFileEntity;
import com.JPA.Board.Entity.BoardEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
@NoArgsConstructor //기본 생성자
@ToString
@Getter
@Setter
public class BoardDTO {

    private Long id;

    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;

    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    private int fileAttached;
    private int boardHits;

    private List<MultipartFile> boardFile;
    private List<String> originalFileName;
    private List<String> storedFileName;

    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());

        if (boardEntity.getBoardFileEntityList() == null || boardEntity.getBoardFileEntityList().isEmpty()) {
            boardDTO.setFileAttached(0);
        } else {
            boardDTO.setFileAttached(1);  // 파일이 있을 경우 1로 설정

            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();

            for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
                originalFileNameList.add(boardFileEntity.getOriginalFileName());
                storedFileNameList.add(boardFileEntity.getStoredFileName());
            }

            boardDTO.setOriginalFileName(originalFileNameList);
            boardDTO.setStoredFileName(storedFileNameList);
        }
        return boardDTO;
    }
}