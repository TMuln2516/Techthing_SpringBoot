package com.example.techthing.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyException extends RuntimeException{
    private ErrorCode errorCode;
    public MyException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
