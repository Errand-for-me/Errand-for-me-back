package com.errand.project.domain.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromID;

    @Column(nullable = false)
    private String toID;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Date chatTime;

    @Column(nullable = false)
    private String questTitle;

    @Builder
    public Chat(String fromId, String toId, String content, Date chatTime, String questTitle) {
        this.fromID = fromId;
        this.toID = toId;
        this.content = content;
        this.chatTime = chatTime;
        this.questTitle = questTitle;
    }
}
