package com.errand.project.web;

import com.errand.project.domain.quest.Quest;
import com.errand.project.domain.quest.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestService {
    private final QuestRepository questRepository;

    public Long save(String title, String content, int people, String imageURL, String writer) {
        String new_title = title;
        String new_content = content;
        int new_people = people;
        String new_imageURL = imageURL;
        String new_writer = writer;

        Quest new_article = Quest.builder()
                .title(new_title)
                .content(new_content)
                .people(new_people)
                .imageURL(new_imageURL)
                .writer(new_writer)
                .build();

        return questRepository.save(new_article).getId();
    }

    public List<Quest> findAll() {
        return questRepository.findAll();
    }

}