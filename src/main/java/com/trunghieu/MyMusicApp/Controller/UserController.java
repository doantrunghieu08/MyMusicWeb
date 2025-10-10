package com.trunghieu.MyMusicApp.Controller;

import com.trunghieu.MyMusicApp.DTO.Request.User.LikeSongRequest;
import com.trunghieu.MyMusicApp.DTO.Request.User.UserCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.User.UserUpdateRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.LikedSongResponse;
import com.trunghieu.MyMusicApp.DTO.Response.UserResponse;
import com.trunghieu.MyMusicApp.Mapper.UserMapper;
import com.trunghieu.MyMusicApp.Service.AdminService;
import com.trunghieu.MyMusicApp.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService service;

    @PostMapping("/admin/addUser")
    public ResponseEntity<ApiResponse<UserResponse>> addUser(@RequestBody UserCreationRequest request){
        ApiResponse apiResponse = service.addUser(request);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/admin/deleteUser/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> deleteUser(@PathVariable int id){
        ApiResponse apiResponse = service.deleteUser(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/user/updateUser/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable int id, @RequestBody UserUpdateRequest request){
        ApiResponse apiResponse = service.updateUser(id,request);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/user/likeSong/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> likeSong(@PathVariable int id, @RequestBody LikeSongRequest request){
        ApiResponse apiResponse = service.likeSong(id, request);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/user/stopLiking/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> stopLiking(@PathVariable int id, @RequestBody LikeSongRequest request){
        ApiResponse apiResponse = service.stopLiking(id, request);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/admin/getUserList")
    public ResponseEntity<ApiResponse<UserResponse>> getUserList(){
        ApiResponse apiResponse = service.getUserList();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/user/getLikedSong/{id}")
    public ResponseEntity<ApiResponse<LikedSongResponse>> getLikedSong(@PathVariable int id){
        ApiResponse apiResponse = service.getLikedSongList(id);
        return ResponseEntity.ok(apiResponse);
    }
}
