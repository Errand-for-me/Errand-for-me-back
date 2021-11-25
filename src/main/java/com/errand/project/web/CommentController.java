package com.errand.project.web;

import com.errand.project.domain.comment.Comment;
import com.errand.project.domain.comment.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public void writeComment(@RequestBody CommentDTO body, HttpServletRequest request) {
        HttpSession session = request.getSession();

        String writer = (String)session.getAttribute("nickname");
        String content = body.getContent();
        Date postTime = new Date();
        String bulletTitle = body.getBulletTitle();

        commentService.save(writer, content, postTime, bulletTitle);
    }

    @GetMapping("/comment")
    public List<Comment> getComments(@RequestParam String bulletTitle) {
        return commentService.getComments(bulletTitle);
    }
}
