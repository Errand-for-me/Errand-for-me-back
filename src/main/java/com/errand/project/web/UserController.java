package com.errand.project.web;

import com.errand.project.domain.user.User;
import com.errand.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping("/sign-in-api")
    public User SignIn(@RequestParam String email, @RequestParam String name, HttpSession session) {
        User user = userService.findOne(email);

        if (user == null) {
            System.out.println("no user");
            User new_User = User.builder()
                    .email(email)
                    .name(name)
                    .build();

            session.setAttribute("email", email);
            session.setAttribute("name", name);
            return new_User;
        } else {
            System.out.println(user.getEmail());
            session.setAttribute("nickname", user.getNickname());
            session.setAttribute("email", email);
            session.setAttribute("name", name);
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
        } else {
            userService.save("null", email, "null", nickname);
        }

        return "redirect:/";
    }

    @PostMapping("/google-sign-in")
    public String GoogleSignin(@RequestBody Map<String, String> body, HttpSession session) {
        String nickname = body.get("nickname");

        String email = (String)session.getAttribute("email");
        String name = (String)session.getAttribute("name");

        System.out.println(email);
        System.out.println(name);

        return "test";
    }
}
