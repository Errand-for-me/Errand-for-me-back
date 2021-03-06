package com.errand.project.web;

import com.errand.project.domain.board.Board;
import com.errand.project.domain.board.BoardIdDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @GetMapping("/bullet")
    public Board getBullet(@RequestParam Long id) {
        return boardService.findOne(id);
    }

    @RequestMapping(value="/board/write", method = RequestMethod.GET)
    public String boardWrite(@RequestParam String title, @RequestParam String content, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String writer = (String) session.getAttribute("nickname");
        boardService.save(title, content, writer, new Date());

        RedirectView redirect = new RedirectView();
        redirect.setUrl("bulletin");
        return "redirect:/";
    }

    @PostMapping("/board/delete")
    public void deleteBullet(@RequestBody BoardIdDTO body) {
        Long bulletId = body.getId();

        boardService.deleteById(bulletId);
    }

    @PostMapping("/board")
    public RedirectView boardPost(@RequestBody MultiValueMap<String, String> requestBody, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String writer = (String) session.getAttribute("nickname");
        String title = requestBody.get("title").get(0);
        String content = requestBody.get("content").get(0);

        boardService.save(title, content, writer, new Date());

        RedirectView redirect = new RedirectView();
        redirect.setUrl("");
        return redirect;
    }

}
