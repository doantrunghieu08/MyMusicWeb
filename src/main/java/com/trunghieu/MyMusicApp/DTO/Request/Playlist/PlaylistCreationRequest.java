package com.trunghieu.MyMusicApp.DTO.Request.Playlist;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.trunghieu.MyMusicApp.Entity.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaylistCreationRequest {

    int user_id;

    String name;

    String description;

    String image_url;

    List<Integer> songIDs;;
}
