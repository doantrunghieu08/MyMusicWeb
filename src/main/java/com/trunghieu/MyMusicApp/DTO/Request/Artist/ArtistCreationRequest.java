package com.trunghieu.MyMusicApp.DTO.Request.Artist;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.trunghieu.MyMusicApp.Entity.Song;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtistCreationRequest {

    String name;


    String bio;


    String image_url;

}
