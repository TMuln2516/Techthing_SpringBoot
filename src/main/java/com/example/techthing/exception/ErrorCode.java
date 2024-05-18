package com.example.techthing.exception;

public enum ErrorCode {
    // invoice
    INVOICE_EXISTED(400, "Invoice existed"),
    INVOICE_NOT_EXISTED(400, "Invoice not existed"),
    // product
    NAME_PRODUCT_EXISTED(400, "Name of Product existed"),
    PRODUCT_NOT_EXISTED(400, "Product not existed"),
    PRODUCT_EXISTED(400, "Product existed"),
    // category
    CATEGORY_EXISTED(400, "Category existed"),
    CATEGORY_NOT_EXISTED(400, "Category not existed"),
    // user or auth
    USER_EXISTED(400, "User existed"),
    EMAIL_ALREADY_IN_USE(400, "Email is already in use"),
    USER_NOT_EXISTED(400, "User not existed"),
    PASSWORD_NOT_MATCH(400, "Password not match"),
    PASSWORD_OR_USERNAME_INCORRECT(400, "Username or Password Incorrect"),
    INVALID_PASS(400, "Invalid Password"),
    INVALID(400, "Invalid Parameter"),
    UNAUTHENTICATED(400, "Unauthenticated"),
    MISSING_UPPERCASE(400, "Password must contain 1 or more uppercase characters."),
    MISSING_LOWERCASE(400, "Password must contain 1 or more lowercase characters."),
    MISSING_SPECIAL_CHARACTERS(400, "Password must contain 1 or more special characters."),
    ILLEGAL_NUMERICAL_SEQUENCE(400, "Password contains the illegal numerical sequence '123456'."),
    PASSWORD_LENGTH(400, "Password must be 8 or more characters in length."),
    UNAUTHORIZED(400, "You do not have permission"),
    ADDRESS_NOT_EXISTED(400, "Address not existed"),
    INVALID_OTP(400, "Invalid OTP"),
    EXPIRED_OTP(400, "Expired OTP"),
    PASSWORD_INCORRECT(400, "Password Incorrect"),
    TOKEN_INVALID(400, "Token Invalid");

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
