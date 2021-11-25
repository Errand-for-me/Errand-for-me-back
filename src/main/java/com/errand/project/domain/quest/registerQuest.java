package com.errand.project.domain.quest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class registerQuest {
    private MultipartFile image;
    private String title;
    private String content;
    private int people;
    private String writer;
    private float lat;
    private float lng;
    private int payment;
}
