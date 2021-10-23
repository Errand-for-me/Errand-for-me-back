package com.errand.project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/test")
    public String test(@RequestParam String title, @RequestParam String content) {
        boardService.save(title, content);
        return "hello";
    }
}
