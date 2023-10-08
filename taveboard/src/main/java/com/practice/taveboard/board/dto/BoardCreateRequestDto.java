package com.practice.taveboard.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class BoardCreateRequestDto {

    private String title;
    private String content;
}