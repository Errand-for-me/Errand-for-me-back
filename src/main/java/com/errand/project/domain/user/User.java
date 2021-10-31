package com.errand.project.domain.user;

import com.errand.project.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(length = 11)
    private String phoneNumber;

    @Column(length = 500)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ErrandType errandType;

    @Column(nullable = false)
    private String grade = "Bronze";

    @Column(nullable = false)
    private int gradeCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, Role role, ErrandType errandType) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.errandType = errandType;
    }

    public User update(String name) {
        this.name = name;

        return this;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }
}
