package com.trunghieu.MyMusicApp.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LikedSongResponse {
    int id;

    String title;

    int duration;

    String file_url;;

    Date created_at;

    String artistName;

    String genreName;
}
