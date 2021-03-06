package com.errand.project.web;

import com.errand.project.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    @GetMapping("/logOut")
    public void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }

    @GetMapping("/isLogin")
    public User isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("nickname") == null) {
            return new User();
        } else {
            User new_User = User.builder()
                    .email((String) session.getAttribute("email"))
                    .name((String) session.getAttribute("name"))
                    .nickname((String) session.getAttribute("nickname"))
                    .build();
            return new_User;
        }
    }

    @GetMapping("/sign-in-api")
    public User SignIn(@RequestParam String email, @RequestParam String name, HttpServletRequest request) {
        User user = userService.findOne(email);

        HttpSession session = request.getSession();
        if (user == null) {
            User new_User = User.builder()
                    .email(email)
                    .name(name)
                    .build();

            session.setAttribute("email", email);
            session.setAttribute("name", name);
            return new_User;
        } else {
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

        User lookupDB = userService.findByNickname(nickname);

        if (lookupDB != null) {
            System.out.println("req fail!");
            return "false";
        }
        if (email == null) {
            userService.save(name, "null", password, nickname);
        } else {
            userService.save("null", email, "null", nickname);
        }

        return "redirect:/";
    }

    @PostMapping("/google-sign-in")
    public String GoogleSignin(@RequestBody Map<String, String> body, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String nickname = body.get("nickname");

        User lookupDB = userService.findByNickname(nickname);

        if (lookupDB != null) {
            System.out.println("req fail!");
            return "false";
        }

        session.setAttribute("nickname", nickname);
        session.setAttribute("isLogin", true);

        String email = (String) session.getAttribute("email");
        String name = (String) session.getAttribute("name");

        userService.save(name, email, null, nickname);

        return "true";
    }
}
