package com.trunghieu.MyMusicApp.Service;

import com.trunghieu.MyMusicApp.DTO.Request.Playlist.AddSongToPlaylistRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Playlist.DeleteSongFromPlaylistRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Playlist.PlaylistCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Playlist.UpdatePlaylistRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.PlaylistResponse;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Entity.Playlist;
import com.trunghieu.MyMusicApp.Entity.Song;
import com.trunghieu.MyMusicApp.Entity.User;
import com.trunghieu.MyMusicApp.Mapper.PlaylistMapper;
import com.trunghieu.MyMusicApp.Mapper.SongMappper;
import com.trunghieu.MyMusicApp.Repository.PlaylistRepository;
import com.trunghieu.MyMusicApp.Repository.SongRepository;
import com.trunghieu.MyMusicApp.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlaylistService {
    PlaylistRepository repository;
    UserRepository userRepository;
    SongRepository songRepository;
    SongMappper songMapper;


    PlaylistMapper mapper;
    public ApiResponse<PlaylistResponse> addPlayList(PlaylistCreationRequest request){
        User user = userRepository.findById(request.getUser_id()).orElseThrow(() -> new RuntimeException("Không có user"));

        Playlist playlist = new Playlist();
        playlist.setName(request.getName());
        playlist.setUser(user);
        playlist.setName(request.getName());
        playlist.setDescription(request.getDescription());
        playlist.setCreated_at(new Date());

        List<Song> songs = songRepository.findAllById(request.getSongIDs());
        playlist.setSongs(songs);
        repository.save(playlist);
        return new ApiResponse<>(200, "Tạo thành công playlist", mapper.toResponse(playlist));
    }
    public ApiResponse<PlaylistResponse> addSongToPlaylist(AddSongToPlaylistRequest song, int playlist_id){
        Playlist playlist = repository.findById(playlist_id).orElseThrow(() -> new RuntimeException("playlist không tồn tại"));

        List<AddSongToPlaylistRequest> list = mapper.toSongRequest(playlist.getSongs());
        list.add(song);
        List<Song> songList = mapper.toSong(list);
        playlist.setSongs(songList);
        repository.save(playlist);
        return new ApiResponse<>(200, "Thêm bài hát thành công", mapper.toResponse(playlist));
    }
    public ApiResponse<PlaylistResponse> deleteSongFromPlaylist(@PathVariable int playlist_id, @PathVariable DeleteSongFromPlaylistRequest request){
        Playlist playlist = repository.findById(playlist_id).orElseThrow(() -> new RuntimeException("playlist không tồn tại"));
        playlist.getSongs().removeIf(song -> song.getId() == request.getId());
        repository.save(playlist);
        return new ApiResponse<>(200, "Xóa thành công", mapper.toResponse(playlist));
    }
    public ApiResponse<PlaylistResponse> deletePlaylist(int id){
       Playlist playlist = repository.findById(id).orElseThrow(() -> new RuntimeException("Không có playlist"));
       PlaylistResponse response = mapper.toResponse(playlist);
       repository.delete(playlist);
       return new ApiResponse<>(200, "Xóa playlist thành công", response);
    }
    public ApiResponse<PlaylistResponse> updatePlaylist(int id, UpdatePlaylistRequest request){
        Playlist playlist = repository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy playlist"));
        playlist.setName(request.getName());
        playlist.setDescription(request.getDescription());
        playlist.setImage_url(request.getImage_url());
        List<Song> songs = songRepository.findAllById(request.getSongIDs());
        playlist.setSongs(songs);
        repository.save(playlist);
        return new ApiResponse<>(200, "Sửa thành công", mapper.toResponse(playlist));
    }


    public ApiResponse<List<PlaylistResponse>> getPlaylistList(int user_id){

        List<Playlist> playlists = repository.findAllByUserId(user_id);
        List<PlaylistResponse> playlistResponses = mapper.toPlaylistResponseList(playlists);
        return new ApiResponse<>(200, "Danh sách playlist", playlistResponses);
    }


    public ApiResponse<List<PlaylistResponse>> getPlaylistList(){

        List<Playlist> playlists = repository.findAll();
        List<PlaylistResponse> playlistResponses = mapper.toPlaylistResponseList(playlists);
        return new ApiResponse<>(200, "Danh sách playlist", playlistResponses);
    }

    public ApiResponse<List<SongResponse>> getSong(int id){
        Playlist playlist = repository.findById(id).orElseThrow(() -> new RuntimeException("Không có playlist"));
        List<SongResponse> songResponseList = songMapper.toSongResponseList(playlist.getSongs());
        return new ApiResponse<>(200, "Danh sách nhạc", songResponseList);
    }
    public ApiResponse<PlaylistResponse> searchPlaylist(int playlist_id){
        Playlist playlist = repository.findById(playlist_id).orElseThrow(() ->new RuntimeException("Không có playlist"));
        return new ApiResponse<>(200, "Play list cần tìm", mapper.toResponse(playlist));
    }

}
