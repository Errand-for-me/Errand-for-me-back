package com.errand.project.domain.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date postTime;

    @Column(nullable = true)
    private String bulletTitle;

    @Builder
    public Comment(String writer, String content, Date postTime, String bulletTitle) {
        this.writer = writer;
        this.content = content;
        this.postTime = postTime;
        this.bulletTitle = bulletTitle;
    }

}
