package com.trunghieu.MyMusicApp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.trunghieu.MyMusicApp.DTO.Response.UserResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;


    int duration;


    String file_url;;


    Date created_at;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonBackReference
    Artist artist;

    String image_url;

    @ManyToMany(mappedBy = "likedSongs")
    @JsonBackReference
    List<User> likedByUser;

    @ManyToMany(mappedBy = "songs", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Playlist> playlists = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonBackReference
    Genre genre;
}
