package com.trunghieu.MyMusicApp.Controller;


import com.trunghieu.MyMusicApp.DTO.Request.User.UserCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.UserResponse;
import com.trunghieu.MyMusicApp.Service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {
    AdminService userService;
    @PostMapping("admin/addUser")
    ResponseEntity<ApiResponse<UserResponse>> addUser(@RequestBody UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = userService.addUser(request);
        return ResponseEntity.ok(apiResponse);
    }
}
