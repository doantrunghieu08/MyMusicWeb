package com.trunghieu.MyMusicApp.Exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter

public enum ErrorCode {
    USERNAME_VALIDATION(101, "username phải trên 8 kí tự"),
    PASSWORD_VALIDATION(102, "Password phải trên 8 kí tự"),
    EMAIL_VALIDATION(103, "Không đúng định dạng email"),
    UNDEFINED_EXCEPTION(999, "Lỗi không xác định"),
    ;
    int code;
    String messagge;

    ErrorCode(int code, String messagge) {
        this.code = code;
        this.messagge = messagge;
    }
}
