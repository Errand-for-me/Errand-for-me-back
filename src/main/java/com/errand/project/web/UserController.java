package com.errand.project.web;

import com.errand.project.domain.user.User;
import com.errand.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping("/sign-in-api")
    public User SignIn(@RequestParam String email, @RequestParam  String name) {
        User user = userService.findOne(email);

        if (user == null) {
            System.out.println("no user");
            User new_User = User.builder()
                    .email(email)
                    .name(name)
                    .build();
            //세션에 이메일 이름 저장하기
            return new_User;
        }
        else {
            System.out.println(user.getEmail());
            //세션 만들기
            return user;
        }
    }

    @PostMapping("/sign-up-api")
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
