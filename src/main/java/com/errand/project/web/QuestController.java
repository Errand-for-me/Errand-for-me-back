package com.errand.project.web;

import com.errand.project.domain.quest.Quest;
import com.errand.project.domain.quest.registerQuest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class QuestController {

    private final QuestService questService;

    @GetMapping("/quest")
    public List<Quest> questGet() { return questService.findAll(); }

    @PostMapping("/quest")
    public RedirectView questPost(@ModelAttribute registerQuest request) {
        System.out.println(request.getPeople());
        System.out.println(request.getImage().getOriginalFilename());
        System.out.println(request.getTitle());

        MultipartFile file = request.getImage();;

        RedirectView redirect = new RedirectView();
        redirect.setUrl("");
        return redirect;
    }
}
