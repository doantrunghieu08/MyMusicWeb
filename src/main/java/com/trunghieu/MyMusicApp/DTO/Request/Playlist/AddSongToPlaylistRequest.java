package com.trunghieu.MyMusicApp.DTO.Request.Playlist;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddSongToPlaylistRequest {
    int id;
}
