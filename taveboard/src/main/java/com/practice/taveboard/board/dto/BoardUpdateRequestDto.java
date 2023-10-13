package com.practice.taveboard.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardUpdateRequestDto {

    private String title;
    private String content;
}
