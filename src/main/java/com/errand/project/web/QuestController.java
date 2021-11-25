package com.errand.project.web;

import com.errand.project.domain.quest.Quest;
import com.errand.project.domain.quest.QuestAcceptDTO;
import com.errand.project.domain.quest.registerQuest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
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

    @GetMapping("/quest-info")
    public Optional<Quest> getQuestInfo(@RequestParam Long id) { return questService.findById(id); }

    @PostMapping("/quest-accept")
    public int questReceiver(HttpServletRequest request, @RequestBody QuestAcceptDTO body) {

        HttpSession session = request.getSession();
        String receiver = (String)session.getAttribute("nickname");

        return questService.updateReceiver(receiver, body.getId());

    }

    @PostMapping("/quest")
    public RedirectView questPost(@ModelAttribute registerQuest request, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String writer = (String)session.getAttribute("nickname");

        MultipartFile file = request.getImage();
        StringBuilder sb = new StringBuilder();

        String title = request.getTitle();
        String content = request.getContent();
        int people = request.getPeople();
        String filename = request.getImage().getOriginalFilename();

        float lng = request.getLng();
        float lat = request.getLat();
        int payment = request.getPayment();

        if (filename == "") filename = "none.svg";

        questService.save(title, content, people, filename, writer, lat, lng, payment);

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
