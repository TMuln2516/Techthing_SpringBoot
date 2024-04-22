package com.example.techthing.exception;

public enum ErrorCode {
    USER_EXISTED(400, "User existed"),
    USER_NOT_EXISTED(400, "User not existed"),
    PASSWORD_INCORRECT(400, "Password incorrect"),
    INVALID_PASS(400, "Invalid Password"),
    UNAUTHENTICATED(400, "Unauthenticated"),
    MISSING_UPPERCASE(400, "Password must contain 1 or more uppercase characters."),
    MISSING_LOWERCASE(400, "Password must contain 1 or more lowercase characters."),
    MISSING_SPECIAL_CHARACTERS(400, "Password must contain 1 or more special characters."),
    ILLEGAL_NUMERICAL_SEQUENCE(400,"Password contains the illegal numerical sequence '123456'."),
    PASSWORD_LENGTH(400, "Password must be 8 or more characters in length."),
    UNAUTHORIZED(400, "You do not have permission")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private int code;
    private String message;
}
