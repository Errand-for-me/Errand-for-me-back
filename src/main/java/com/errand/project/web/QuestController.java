package com.errand.project.web;

import com.errand.project.domain.quest.Quest;
import com.errand.project.domain.quest.registerQuest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class QuestController {

    private final QuestService questService;

    @GetMapping("/img/{filename}")
    public ResponseEntity<Resource> viewImg(@PathVariable("filename") String imgname) throws IOException {
        String _path = System.getProperty("user.dir");
        String inputFile = _path + "/img/" + imgname;
        Path path = new File(inputFile).toPath();
        FileSystemResource resource = new FileSystemResource(path);

        if(!resource.exists()) {
            inputFile = _path + "/img/none.svg";
            path = new File(inputFile).toPath();
            resource = new FileSystemResource(path);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                .body(resource);
    }

    @GetMapping("/quest")
    public List<Quest> questGet() { return questService.findAll(); }

    @PostMapping("/quest")
    public RedirectView questPost(@ModelAttribute registerQuest request) {
        MultipartFile file = request.getImage();
        StringBuilder sb = new StringBuilder();

        String title = request.getTitle();
        String content = request.getContent();
        int people = request.getPeople();
        String filename = request.getImage().getOriginalFilename();
        if (filename == "") filename = "none.svg";
        String writer = "임시 유저 이름";

        questService.save(title, content, people, filename, writer);

        if (file.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(file.getOriginalFilename());
        }

        if (!file.isEmpty()) {
            String path = System.getProperty("user.dir");
            File dest = new File(path + "/img/" + sb.toString());
            try {
                file.transferTo(dest);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        RedirectView redirect = new RedirectView();
        redirect.setUrl("");
        return redirect;
    }
}
