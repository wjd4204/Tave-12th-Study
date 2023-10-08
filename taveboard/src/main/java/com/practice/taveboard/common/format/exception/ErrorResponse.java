package com.practice.taveboard.common.format.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

public class ErrorResponse {

    private String message;
    private String code;
    private int status;
    private LocalDateTime time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> errors;

    public ErrorResponse(ApplicationRunException e){
        this.message = e.getMessage();
        this.code = e.getErrorEnumCode().getCode();
        this.status = HttpStatus.BAD_REQUEST.value();
        this.time = now();
    }

    public static ErrorResponse of(ApplicationRunException e){
        return new ErrorResponse(e);
    }
}
