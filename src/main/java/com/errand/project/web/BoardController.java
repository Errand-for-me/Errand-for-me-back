package com.errand.project.web;

import com.errand.project.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public List<Board> boardGet() {
        return boardService.findAll();
    }

    @RequestMapping(value="/board/write", method = RequestMethod.GET)
    public String boardWrite(@RequestParam String title, @RequestParam String content) {
        boardService.save(title, content);

        RedirectView redirect = new RedirectView();
        redirect.setUrl("bulletin");
        return "redirect:/";
    }

    @PostMapping("/board")
    public RedirectView boardPost(@RequestBody MultiValueMap<String, String> requestBody) {

        String title = requestBody.get("title").get(0);
        String content = requestBody.get("content").get(0);

        boardService.save(title, content);

        RedirectView redirect = new RedirectView();
        redirect.setUrl("");
        return redirect;
    }

}
