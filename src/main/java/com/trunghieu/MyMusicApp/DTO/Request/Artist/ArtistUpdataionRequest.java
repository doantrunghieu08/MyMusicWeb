package com.trunghieu.MyMusicApp.DTO.Request.Artist;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtistUpdataionRequest {
    String name;


    String bio;


    String image_url;
}
