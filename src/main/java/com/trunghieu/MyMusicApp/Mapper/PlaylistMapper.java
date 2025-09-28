package com.trunghieu.MyMusicApp.Mapper;

import com.trunghieu.MyMusicApp.DTO.Request.Playlist.AddSongToPlaylistRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Playlist.PlaylistCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.PlaylistResponse;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Entity.Playlist;
import com.trunghieu.MyMusicApp.Entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    @Mapping(source = "user.id", target = "userID")
    public PlaylistResponse toResponse(Playlist playlist);


    @Mapping(source = "user_id", target = "user.id")
    public Playlist toPlaylist(PlaylistCreationRequest request);


    public List<AddSongToPlaylistRequest> toSongRequest(List<Song> song);


    public List<Song> toSong (List<AddSongToPlaylistRequest> list);

    @Mapping(source = "user.id", target = "userID")
    public List<PlaylistResponse> toPlaylistResponseList(List<Playlist> playlists);
}
