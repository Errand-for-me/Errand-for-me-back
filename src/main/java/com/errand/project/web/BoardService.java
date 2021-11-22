package com.errand.project.web;

import com.errand.project.domain.board.Board;
import com.errand.project.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Long save(String title, String content, String writer) {
        String new_title = title;
        String new_content = content;
        String new_writer = writer;

        Board new_article = Board.builder()
                .title(new_title)
                .content(new_content)
                .writer(new_writer)
                .build();

        return boardRepository.save(new_article).getId();
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findOne(Long id) { return boardRepository.findById(id).get(); }

}
