package com.practice.taveboard.board.entity;

import com.practice.taveboard.common.domain.BaseTimeEntity;
import com.practice.taveboard.user.entity.User;
import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BOARD_TABLE")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name = "nickname")
    private User user;

    public void update(String title, String content){
        this.title = title;
        this.content = content;

    }
}
