package com.trunghieu.MyMusicApp.Service;

import com.trunghieu.MyMusicApp.DTO.Request.LoginRequest;
import com.trunghieu.MyMusicApp.DTO.Request.User.UserCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.UserResponse;
import com.trunghieu.MyMusicApp.Entity.User;
import com.trunghieu.MyMusicApp.Mapper.UserMapper;
import com.trunghieu.MyMusicApp.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminService {
    UserRepository repository;
    UserMapper userMapper;


    public ApiResponse<UserResponse> addUser(UserCreationRequest request){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encoder = passwordEncoder.encode(request.getPassword());
        User user = userMapper.toUser(request);
        user.setPassword(encoder);
        repository.save(user);

       return new ApiResponse<>(100, "Thêm thành cônng", userMapper.toUserResponse(user));


    }
    public ApiResponse<UserResponse> authUser(LoginRequest request){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<User> optionalUser = repository.findByUsername(request.getUsername());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
                return new ApiResponse<>(200, "Đăng nhập thành công", userMapper.toUserResponse(user));
            }
        }

        return new ApiResponse<>(404, "Thông tin đăng nhập sai", null);

    }
}
