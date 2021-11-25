package com.errand.project.domain.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c WHERE c.questTitle = :questTitle ORDER BY c.chatTime DESC")
    List<Chat> getChatHistoryByName(String questTitle);

}
