package com.trunghieu.MyMusicApp.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class PlaylistResponse {

    int id;

    int userID;

    String name;

    String description;

    String image_url;

    List<SongResponse> songs;
}
