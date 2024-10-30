package com.JPA.Board.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import com.JPA.Board.Service.CommentService;
import lombok.RequiredArgsConstructor;
import com.JPA.Board.DTO.CommentDTO;
import java.util.List;

//생성 순서: Controller-> Entity(테이블 생성) -> DTO(테이블을 서버에 전달) -> Repository(extends JPA)-> Service(Repository)
//호출 순서: Controller -> Service-> Repository -> Entity

@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    public String save(@ModelAttribute CommentDTO comment) {
        Long saveResult = commentService.save(comment);

        if(saveResult != null){
            List<CommentDTO> commentDTOList = commentService.findAll(comment.getBoardId());
        } else return "작성 실패";

        return "redirect:/board/comment";
    }
}