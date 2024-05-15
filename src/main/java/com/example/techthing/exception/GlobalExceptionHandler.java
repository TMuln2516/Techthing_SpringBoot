package com.example.techthing.exception;

import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(400);
        apiResponse.setMessage(exception.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = MyException.class)
    ResponseEntity<ApiResponse<AuthenticationResponse>> handlingMyException(MyException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(
                ApiResponse.<AuthenticationResponse>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getCode()).body(
                ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            ErrorCode errorCode = determineErrorCode(error.getField());
            if (errorCode != null) {
                errors.put(error.getField(), errorCode.getMessage());
            } else {
                errors.put(error.getField(), error.getDefaultMessage());
            }
        });
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .code(400)
                        .message(ErrorCode.INVALID.getMessage())
                        .result(errors)
                        .build());
    }

    private ErrorCode determineErrorCode(String fieldName) {
        if ("length".equals(fieldName)) {
            return ErrorCode.PASSWORD_LENGTH;
        } else if ("uppercase".equals(fieldName)) {
            return ErrorCode.MISSING_UPPERCASE;
        } else if ("lowercase".equals(fieldName)) {
            return ErrorCode.MISSING_UPPERCASE;
        } else if ("special".equals(fieldName)) {
            return ErrorCode.MISSING_SPECIAL_CHARACTERS;
        } else if ("numerical".equals(fieldName)) {
            return ErrorCode.ILLEGAL_NUMERICAL_SEQUENCE;
        }
        return null;
    }
}
