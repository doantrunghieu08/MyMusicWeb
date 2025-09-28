package com.trunghieu.MyMusicApp.DTO.Request.Genre;

import com.trunghieu.MyMusicApp.Entity.Song;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreUpdationRequest {
    String name;
    String description;
    List<Integer> songList;

}
