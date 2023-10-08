package com.practice.taveboard.common.format.exception.board.errorCode;

import com.practice.taveboard.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardErrorCode implements ErrorEnumCode {

    NOT_FOUND_BOARD("B001", "해당 게시글을 찾을 수 없습니다.");

    private final String code;
    private final String message;
}
