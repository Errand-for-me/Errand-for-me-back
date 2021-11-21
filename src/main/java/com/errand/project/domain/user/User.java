package com.errand.project.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String email;

    @Column(length = 500, nullable = false)
    private String password;

    @Column(length = 500, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(length = 500)
    private ErrandType errandType;

    @Builder
    public User(String name, String email, String password, String nickname) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

}
