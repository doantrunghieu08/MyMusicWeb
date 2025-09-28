package com.trunghieu.MyMusicApp.Controller;

import com.trunghieu.MyMusicApp.DTO.Request.Playlist.AddSongToPlaylistRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Playlist.DeleteSongFromPlaylistRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Playlist.PlaylistCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Playlist.UpdatePlaylistRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.PlaylistResponse;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Service.PlaylistService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class PlaylistController {
    PlaylistService service;

    @PostMapping("/createPlaylist")
    public ResponseEntity<ApiResponse<PlaylistResponse>> createPlaylist(@RequestBody PlaylistCreationRequest request){
        ApiResponse apiResponse = service.addPlayList(request);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/addSong/{id}")
    public ResponseEntity<ApiResponse<PlaylistResponse>> addSongToPlaylist(@PathVariable int id, @RequestBody AddSongToPlaylistRequest request){
        ApiResponse apiResponse = service.addSongToPlaylist(request, id);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/deleteSong/{id}")
    public ResponseEntity<ApiResponse<PlaylistResponse>> deleteSongFromPlaylist(@PathVariable int id, @RequestBody DeleteSongFromPlaylistRequest request){
        ApiResponse apiResponse = service.deleteSongFromPlaylist(id, request);
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/deletePlaylist/{id}")
    public ResponseEntity<ApiResponse<PlaylistResponse>> deletePlaylist(@PathVariable int id){
        ApiResponse apiResponse = service.deletePlaylist(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/editPlaylist/{id}")
    public ResponseEntity<ApiResponse<PlaylistResponse>> editPlayList(@PathVariable int id, @RequestBody UpdatePlaylistRequest request){
        ApiResponse apiResponse = service.updatePlaylist(id, request);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/getPlaylistByUserId/{user_id}")
    public ResponseEntity<ApiResponse<PlaylistResponse>> getPlaylistByUserID(@PathVariable int user_id){
        ApiResponse apiResponse = service.getPlaylistList(user_id);
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/updatePlaylist/{id}")
    public ResponseEntity<ApiResponse<PlaylistResponse>> updatePlaylist(@PathVariable int id, @RequestBody UpdatePlaylistRequest request){
        ApiResponse apiResponse = service.updatePlaylist(id, request);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/getPlaylistList")
    public ResponseEntity<ApiResponse<PlaylistResponse>> getPlaylistList(){
        ApiResponse apiResponse = service.getPlaylistList();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSong/{id}")
    public ResponseEntity<ApiResponse<List<SongResponse>>> getSong(@PathVariable int id){
        ApiResponse apiResponse =service.getSong(id);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/searchPlaylist/{playlist_id}")
    public ResponseEntity<ApiResponse<PlaylistResponse>> searchPlaylist(@PathVariable int playlist_id){
        ApiResponse apiResponse = service.searchPlaylist(playlist_id);
        return ResponseEntity.ok(apiResponse);
    }
}
