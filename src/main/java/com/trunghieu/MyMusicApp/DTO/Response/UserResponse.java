package com.trunghieu.MyMusicApp.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trunghieu.MyMusicApp.Entity.Playlist;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    int userID;

    String username;


    String password;


    String email;


    Date created_at;

    List<PlaylistResponse> playsitList;

    String role;
}
