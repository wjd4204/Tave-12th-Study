package com.practice.taveboard.common.format.exception.board;

import com.practice.taveboard.common.format.exception.ApplicationRunException;
import com.practice.taveboard.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.practice.taveboard.common.format.exception.board.errorCode.BoardErrorCode.NOT_FOUND_BOARD;

public class NotFoundBoardException extends ApplicationRunException {

    private static final ErrorEnumCode CODE = NOT_FOUND_BOARD;

    private NotFoundBoardException(ErrorEnumCode CODE){
        super(CODE);
    }

    public NotFoundBoardException(){
        this(CODE);
    }
}
