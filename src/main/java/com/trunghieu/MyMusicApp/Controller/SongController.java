package com.trunghieu.MyMusicApp.Controller;


import com.trunghieu.MyMusicApp.DTO.Request.Song.SongAdditationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Song.SongUpdationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Service.SongService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SongController {
    SongService service;
    @GetMapping("/getSongList")
    public ResponseEntity<ApiResponse<List<SongResponse>>> getSongList(){
        ApiResponse<List<SongResponse>> apiResponse = service.getMusicList();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/user/addSong")
    public ResponseEntity<ApiResponse<SongResponse>> addSong(@RequestBody SongAdditationRequest request){
        ApiResponse apiResponse = service.addMusic(request);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/user/deleteSong/{id}")
    public ResponseEntity<ApiResponse<SongResponse>> deleteSong(@PathVariable int id){
        ApiResponse apiResponse = service.deleteSong(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/user/updateSong/{id}")
    public ResponseEntity<ApiResponse<SongResponse>> updateSong(@PathVariable int id, @RequestBody SongUpdationRequest request){
        ApiResponse apiResponse = service.updateSong(id, request);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchSong/{id}")
    public ResponseEntity<ApiResponse<SongResponse>> findSong(@PathVariable int id){
        ApiResponse apiResponse = service.searchSongByID(id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchSongByKeyword/{keyword}")
    public ResponseEntity<ApiResponse<List<SongResponse>>> searchSong(@PathVariable String keyword){
        ApiResponse apiResponse = service.searchSong(keyword);
        return ResponseEntity.ok(apiResponse);
    }

}
