package com.errand.project.web;

import com.errand.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/sign-in")
    public String SignIn(@RequestParam String email, @RequestParam  String name) {
        userService.findOne(email);

        return "redirect:/";
    }

    @PostMapping("/sign-up")
    public String SignUp(@RequestBody Map<String, String> signupData) {
        String email = signupData.get("email");
        String name = signupData.get("name");
        String nickname = signupData.get("nickname");
        String password = signupData.get("password");

        if (email == null) {
            userService.save(name, "null", password, nickname);
        }
        else {
            userService.save("null", email, "null", nickname);
        }

        return "redirect:/";
    }
}
