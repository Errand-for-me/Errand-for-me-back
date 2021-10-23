package com.errand.project.web;

import com.errand.project.domain.board.Board;
import com.errand.project.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Long save(String title, String content) {
        String new_title = title;
        String new_content = content;

        Board new_article = Board.builder()
                .title(new_title)
                .content(new_content)
                .build();

        return boardRepository.save(new_article).getId();
    }

}
