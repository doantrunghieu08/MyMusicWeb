package com.trunghieu.MyMusicApp.Controller;

import com.trunghieu.MyMusicApp.DTO.Request.LoginRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.UserResponse;
import com.trunghieu.MyMusicApp.Mapper.UserMapper;
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
    @PostMapping("/login")
    ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody LoginRequest request){
        try{
            // Kiểm tra xem username và password có chính xác hay không ?
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );

            //Khi xác nhận là chính xác thì lưu thông tin đăng nhập vào SecurityContextHolder: username, password, role
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok(new ApiResponse<>(200, "Đăng nhập thành công", mapper.toUserResponseFromLoginRequest(request)));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(404, "Thông tin đăng nhập sai", null));
        }
    }
}
