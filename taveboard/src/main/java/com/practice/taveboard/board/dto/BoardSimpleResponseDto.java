package com.practice.taveboard.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class BoardSimpleResponseDto {

    private String title;
    private String content;
    private String username;
    private LocalDateTime createdTime;
}
