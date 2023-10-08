package com.practice.taveboard.board.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class BoardListResponseDto {

    private int totalPage;
    private int currentPage;
    private List<BoardSimpleResponseDto> list;
}
