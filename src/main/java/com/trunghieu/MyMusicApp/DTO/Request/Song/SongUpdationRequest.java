package com.trunghieu.MyMusicApp.DTO.Request.Song;

import com.trunghieu.MyMusicApp.Entity.Artist;
import com.trunghieu.MyMusicApp.Entity.Genre;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SongUpdationRequest {
    @NotNull
    String title;

    @NotNull
    int duration;

    @NotNull
    String file_url;


    Date created_at;

    String image_url;

    int artistID;

    int genreID;

}
