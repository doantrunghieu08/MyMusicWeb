package com.trunghieu.MyMusicApp.Controller;

import com.trunghieu.MyMusicApp.DTO.Request.LoginRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.LoginResponse;
import com.trunghieu.MyMusicApp.DTO.Response.UserResponse;
import com.trunghieu.MyMusicApp.Mapper.UserMapper;
import com.trunghieu.MyMusicApp.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthenticationManager authenticationManager;
    UserMapper mapper;
    UserService service;
    @PostMapping("/login")
    ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request){
        try{
            // Kiểm tra xem username và password có chính xác hay không ?
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );


            //Khi xác nhận là chính xác thì lưu thông tin đăng nhập vào SecurityContextHolder: username, password, role
            SecurityContextHolder.getContext().setAuthentication(authentication);

            ApiResponse apiResponse = service.login(request);
            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(404, "Thông tin đăng nhập sai", null));
        }
    }
}
