package com.trunghieu.MyMusicApp.DTO.Request.Playlist;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePlaylistRequest {
    int user_id;

    String name;

    String image_url;

    List<Integer> songIDs;

    String description;
}
