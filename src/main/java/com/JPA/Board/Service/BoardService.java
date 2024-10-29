package com.JPA.Board.Service;

import org.springframework.web.multipart.MultipartFile;
import com.JPA.Board.Repository.BoardFileRepository;
import org.springframework.data.domain.PageRequest;
import com.JPA.Board.Repository.BoardRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import com.JPA.Board.Entity.BoardFileEntity;
import jakarta.transaction.Transactional;
import com.JPA.Board.Entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import com.JPA.Board.DTO.BoardDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.io.File;

//DTO -> Entity (Service)
//Entity -> DTO (DTOClass)

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        if(boardDTO.getBoardFile().isEmpty()){
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);  // 저장
        }
        else{
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long saveId = boardRepository.save(boardEntity).getId();
            BoardEntity board = boardRepository.findById(saveId).get();

            for(MultipartFile boardFile : boardDTO.getBoardFile()){

                String originalFilename = boardFile.getOriginalFilename();                  //원래 파일 이름
                String storedFilename = System.currentTimeMillis()+"_"+originalFilename;    //서버 저장용 파일 이름
                String savePath="C:/Users/wjaud/OneDrive/바탕 화면/MOST IMPORTANT/WIT_TEST/file/"+storedFilename;
                boardFile.transferTo(new File(savePath));

                BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFilename);
                boardFileRepository.save(boardFileEntity);
            }
        }
    }

    @Transactional  //없으면 에러 뜸
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for(BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public void updatehits(Long id) {
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);

        if(boardEntityOptional.isPresent()) {
            BoardEntity boardEntity = boardEntityOptional.get();
            BoardDTO bd = BoardDTO.toBoardDTO(boardEntity);
            return bd;
        }
        else return null;
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);

        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page=pageable.getPageNumber()-1;
        int PageLimit=10;
        Page<BoardEntity> boardEntities=
           boardRepository.findAll(PageRequest.of(page, PageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<BoardDTO> boarddtos=boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));

        return boarddtos;
    }
}