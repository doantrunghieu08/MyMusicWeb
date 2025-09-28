package com.trunghieu.MyMusicApp.Exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException{
    ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessagge());
        this.errorCode = errorCode;
    }
}
