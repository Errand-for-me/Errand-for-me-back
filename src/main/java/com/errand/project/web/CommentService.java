package com.errand.project.web;

import com.errand.project.domain.comment.Comment;
import com.errand.project.domain.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Long save(String writer, String content, Date postTime, String bulletTitle) {
        String new_writer = writer;
        String new_content = content;
        Date new_postTime = postTime;
        String new_bulletTitle = bulletTitle;

        Comment new_article = Comment.builder()
                .writer(new_writer)
                .content(new_content)
                .postTime(new_postTime)
                .bulletTitle(new_bulletTitle)
                .build();

        return commentRepository.save(new_article).getId();

    }

    public List<Comment> getComments(String bulletTitle) { return commentRepository.findByBulletTitle(bulletTitle); }
}
