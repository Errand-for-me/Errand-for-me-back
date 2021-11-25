package com.errand.project.web;

import com.errand.project.domain.chat.Chat;
import com.errand.project.domain.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public Long save(String fromId, String toId, String content, Date chatTime, String questTitle) {
        String new_fromId = fromId;
        String new_toId = toId;
        String new_content = content;
        Date new_chatTime = chatTime;
        String new_questTitle = questTitle;

        Chat new_article = Chat.builder()
                .fromId(new_fromId)
                .toId(new_toId)
                .content(new_content)
                .chatTime(new_chatTime)
                .questTitle(new_questTitle)
                .build();

        return chatRepository.save(new_article).getId();
    }

    public List<Chat> getChats(String questTitle) {
        List<Chat> chats = chatRepository.getChatHistoryByName(questTitle);

        return chats;
    }

}
