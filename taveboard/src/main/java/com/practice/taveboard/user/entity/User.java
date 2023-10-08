package com.practice.taveboard.user.entity;

import com.practice.taveboard.common.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "USER_TABLE")
public class User extends BaseTimeEntity {

    @Id
    private String nickname;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;



    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdTime;

}
