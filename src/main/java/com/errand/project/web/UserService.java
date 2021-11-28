package com.errand.project.web;

import com.errand.project.domain.user.User;
import com.errand.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long save(String name, String email, String password, String nickname) {
        String new_name = name;
        String new_email = email;
        String new_password = password;
        String new_nickname = nickname;

        User new_user = User.builder()
                .name(new_name)
                .email(new_email)
                .password(new_password)
                .nickname(new_nickname)
                .build();

        return userRepository.save(new_user).getId();
    }

    public User findOne(String email) {
        List<User> foundUser = userRepository.findByEmail(email);
        User result;
        if (foundUser.size() == 0) result = null;
        else result = foundUser.get(0);

        return result;
    }

    public User findByNickname(String nickname) {
        List<User> foundUser = userRepository.findByNickname(nickname);
        User result;
        if (foundUser.size() == 0) result = null;
        else result = foundUser.get(0);

        return result;
    }
}
