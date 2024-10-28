package com.JPA.Board.Controller;

import com.JPA.Board.DTO.BoardDTO;
import com.JPA.Board.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService bs;

    @GetMapping("/save")
    public String save1() {
        return "/board/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {

        bs.save(boardDTO);
        return "/home";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDTO> boardlist=bs.findAll();
        model.addAttribute("boardlist", boardlist);

        return "/board/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {

        bs.updatehits(id);
        BoardDTO boardDTO= bs.findById(id);
        model.addAttribute("board", boardDTO);
        System.out.println(boardDTO.getBoardHits());
        return "/board/detail";
    }
}