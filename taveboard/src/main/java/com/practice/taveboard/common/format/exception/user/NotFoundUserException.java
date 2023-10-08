package com.practice.taveboard.common.format.exception.user;

import com.practice.taveboard.common.format.exception.ApplicationRunException;
import com.practice.taveboard.common.format.exception.ErrorEnumCode;

import static com.practice.taveboard.common.format.exception.user.errorCode.UserErrorCode.NOT_FOUND_USER;

public class NotFoundUserException extends ApplicationRunException {

    private static final ErrorEnumCode CODE = NOT_FOUND_USER;

    private NotFoundUserException(ErrorEnumCode CODE){
        super(CODE);
    }

    public NotFoundUserException(){
        this(CODE);
    }

}
