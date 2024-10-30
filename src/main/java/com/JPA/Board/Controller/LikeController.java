package com.JPA.Board.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.JPA.Board.Service.LikeService;
import com.JPA.Board.DTO.LikeDTO;
import java.util.List;

@RequestMapping("/like")
@Controller
public class LikeController {

    @GetMapping("/list/{id}")
    public ResponseEntity<List<LikeDTO>> getComments(@PathVariable Long id) {
        List<LikeDTO> likelist = LikeService.findAll(id);
        if (likelist != null && !likelist.isEmpty()) {
            return new ResponseEntity<>(likelist, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
