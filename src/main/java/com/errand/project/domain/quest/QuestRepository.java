package com.errand.project.domain.quest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    @Query("UPDATE Quest q set q.receiver = :receiver WHERE q.id = :id")
    int updateReceiver(String receiver, int id);

    @Query("SELECT q FROM Quest q WHERE q.receiver = :myName OR (q.writer = :myName And q.receiver is not null)")
    List<Quest> findAllChatByName(String myName);
}
