package com.errand.project.web;

import com.errand.project.domain.chat.Chat;
import com.errand.project.domain.chat.ChatDTO;
import com.errand.project.domain.quest.Quest;
import com.errand.project.domain.quest.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class ChatController {

    private final ChatService chatService;
    private final QuestService questService;

    @GetMapping("/chat-title-list")
    public List<Quest> getChatList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = (String)session.getAttribute("nickname");

        List<Quest> titles = questService.findChatList(name);

        return titles;
    }

    @PostMapping("/chat")
    public void postChat(@RequestBody ChatDTO body) {
        String content = body.getContent();
        String receiver = body.getReceiver();
        String sender = body.getSender();
        String title = body.getQuestTitle();
        chatService.save(sender, receiver, content, new Date(), title);
    }

    @GetMapping("/chat-history")
    public List<Chat> getChatHistory(@RequestParam String quest_title) { return chatService.getChats(quest_title); }

}
