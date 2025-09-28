package com.trunghieu.MyMusicApp.Exception;

import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity handlingException(AppException e){
        ApiResponse apiResponse = new ApiResponse<>();

        ErrorCode errorCode = e.getErrorCode();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessagge());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    public ResponseEntity handlingAppException(AppException e){
        ApiResponse apiResponse = new ApiResponse<>();

        ErrorCode errorCode = e.getErrorCode();
        apiResponse.setCode(ErrorCode.UNDEFINED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNDEFINED_EXCEPTION.getMessagge());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
