package com.trunghieu.MyMusicApp.Controller;

import com.trunghieu.MyMusicApp.DTO.Request.Genre.GenreCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Genre.GenreUpdationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.CountGenreResponse;
import com.trunghieu.MyMusicApp.DTO.Response.GenreResponse;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Service.GenreService;
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
public class GenreController {
    GenreService service;

    @PostMapping("/admin/addGenre")
    public ResponseEntity<ApiResponse<GenreResponse>> addGenre(@RequestBody GenreCreationRequest request){
        ApiResponse apiResponse = service.addGenre(request);
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/admin/deleteGenre/{id}")
    public ResponseEntity<ApiResponse<GenreResponse>> deleteGenre(@PathVariable int id){
        ApiResponse apiResponse = service.deleteGenre(id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSongByGenre/{id}")
    public ResponseEntity<ApiResponse<List<SongResponse>>> getSongnByGenre(@PathVariable int id){
        ApiResponse apiResponse = service.getSongByGenre(id);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/getGenreList")
    public ResponseEntity<ApiResponse<GenreResponse>> getGenreList(){
        ApiResponse apiResponse = service.getGenreList();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/admin/countSongByGenre/{id}")
    public ResponseEntity<ApiResponse<CountGenreResponse>> countSongByGenre(@PathVariable int id){
        ApiResponse apiResponse = service.countSongByGenre(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/admin/updateGenre/{id}")
    public ResponseEntity<ApiResponse<GenreResponse>> updateGenre(@PathVariable int id, @RequestBody GenreUpdationRequest request){
        ApiResponse apiResponse = service.updateGenre(id,request);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchGenre/{genreId}")
    public ResponseEntity<ApiResponse<GenreResponse>> searchGenre(@PathVariable int genreId){
        ApiResponse apiResponse =service.searchGenreById(genreId);
        return ResponseEntity.ok(apiResponse);
    }
}
