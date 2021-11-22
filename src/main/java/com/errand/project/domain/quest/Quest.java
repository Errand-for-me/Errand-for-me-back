package com.errand.project.domain.quest;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column()
    private int people;

    @Column(columnDefinition = "TEXT")
    private String imageURL;

    @Column(length = 50)
    private String writer;

    @Column()
    private float lat;

    @Column()
    private float lng;

    @Builder
    public Quest(String title, String content, int people, String imageURL, String writer, float lat, float lng) {
        this.title = title;
        this.content = content;
        this.people = people;
        this.imageURL = imageURL;
        this.writer = writer;
        this.lat = lat;
        this.lng = lng;
    }
}
