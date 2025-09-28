package com.trunghieu.MyMusicApp.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trunghieu.MyMusicApp.Entity.Song;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountGenreResponse {
    int id;

    String name;

    long song_quatity;
}
