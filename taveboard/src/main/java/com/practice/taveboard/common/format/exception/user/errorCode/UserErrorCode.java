package com.practice.taveboard.common.format.exception.user.errorCode;

import com.practice.taveboard.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorEnumCode {

    NOT_FOUND_USER("U001", "해당 유저가 존재하지 않습니다.");

    private final String code;
    private final String message;
}
