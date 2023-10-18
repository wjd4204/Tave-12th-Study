package com.myboard.sbb.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)  // unique = true 는 유일한 값만 저장이 가능하다는 뜻!(중복 불가)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
}
