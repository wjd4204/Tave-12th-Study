package com.practice.taveboard.common.format.exception;

import lombok.Getter;

@Getter
public class ApplicationRunException extends RuntimeException{

    private ErrorEnumCode errorEnumCode;

    public ApplicationRunException(ErrorEnumCode errorEnumCode){
        super(errorEnumCode.getMessage());
        this.errorEnumCode = errorEnumCode;
    }
}
