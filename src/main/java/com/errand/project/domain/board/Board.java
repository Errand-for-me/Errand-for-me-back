package com.errand.project.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    public String writer;

    @Column
    public Date postTime;

    @Builder
    public Board(String title, String content, String writer, Date postTime) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.postTime = postTime;
    }
}
