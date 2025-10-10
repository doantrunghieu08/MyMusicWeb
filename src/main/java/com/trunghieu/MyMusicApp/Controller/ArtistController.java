package com.trunghieu.MyMusicApp.Controller;

import com.trunghieu.MyMusicApp.DTO.Request.Artist.ArtistCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Artist.ArtistUpdataionRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.ArtistResponse;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Entity.Artist;
import com.trunghieu.MyMusicApp.Service.ArtistService;
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
public class ArtistController {
    ArtistService service;
    @GetMapping("/user/findSongByArtist/{id}")
    public ResponseEntity<ApiResponse<List<SongResponse>>> filterSongByArtist(@PathVariable int id){
        ApiResponse apiResponse = service.getsongList(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/admin/addArtist")
    public ResponseEntity<ApiResponse<ArtistResponse>> addArtist(@RequestBody ArtistCreationRequest request){
        ApiResponse apiResponse = service.addArtist(request);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/admin/delArtist/{id}")
    public ResponseEntity<ApiResponse<ArtistResponse>> delArtist(@PathVariable int id){
        ApiResponse response = service.delArtist(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin/updateArtist/{id}")
    public ResponseEntity<ApiResponse<ArtistResponse>> updateArtist(@PathVariable int id, @RequestBody ArtistUpdataionRequest request){
        ApiResponse apiResponse = service.updateArtist(id, request);
        return  ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getArtistList")
    public ResponseEntity<ApiResponse<List<ArtistResponse>>> getArtistList(){
        ApiResponse apiResponse = service.getArtistList();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/user/searchArtist/{keyword}")
    public ResponseEntity<ApiResponse<List<ArtistResponse>>> searchSong(@PathVariable String keyword){
        ApiResponse apiResponse = service.searchArtist(keyword);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/findArtist/{artistID}")
    public ResponseEntity<ApiResponse<ArtistResponse>> findArtistByID(@PathVariable int artistID){
        ApiResponse apiResponse = service.findArtist(artistID);
        return ResponseEntity.ok(apiResponse);
    }
}
