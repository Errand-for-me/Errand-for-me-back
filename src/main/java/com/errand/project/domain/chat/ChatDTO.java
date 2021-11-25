package com.errand.project.domain.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {
    private String content;
    private String sender;
    private String receiver;
    private String questTitle;
}