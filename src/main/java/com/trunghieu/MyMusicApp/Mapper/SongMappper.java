package com.trunghieu.MyMusicApp.Mapper;

import com.trunghieu.MyMusicApp.DTO.Request.Song.SongAdditationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Song.SongUpdationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Entity.Song;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMappper {

    public Song toSong(SongAdditationRequest songRequest);

    @InheritConfiguration(name = "toSongResponseList")
    public List<SongResponse> toSongResponseList (List<Song> songList);

    @Mapping(source = "artist.name", target = "artistName")
    @Mapping(source = "genre.name", target = "genreName")
    public SongResponse toSongResponseFromSong(Song song);

}
