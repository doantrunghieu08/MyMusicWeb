package com.trunghieu.MyMusicApp.DTO.Request.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 8, message = "USERNAME_VALIDATION")
    @NotNull
    String username;

    @Size(min = 8, message = "PASSWORD_VALIDATION")
    @NotNull
    String password;

    @Email(message = "EMAIL_VALIDATION")
    @NotNull
    String email;

}
