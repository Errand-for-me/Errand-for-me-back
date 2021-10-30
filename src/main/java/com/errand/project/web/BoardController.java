package com.errand.project.web;

import com.errand.project.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public List<Board> boardGet() {
        return boardService.findAll();
    }

    @PostMapping("/board")
    public String boardPost(@RequestBody MultiValueMap<String, String> requestBody) {

        String title = requestBody.get("title").get(0);
        String content = requestBody.get("content").get(0);

        boardService.save(title, content);

        return "redirect:/board";
    }

}
