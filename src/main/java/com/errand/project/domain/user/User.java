package com.errand.project.domain.user;

import javax.persistence.*;

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

    @Column(length = 11, nullable = false)
    private String phoneNumber;

    @Column(length = 500, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(length = 500, nullable = false)
    private ErrandType errandType;

    @Column(length = 10, nullable = false)
    private String grade;

    @Column(length = 500, nullable = false)
    private Long gradeCount;

}
