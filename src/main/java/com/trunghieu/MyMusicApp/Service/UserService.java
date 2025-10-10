package com.trunghieu.MyMusicApp.Service;

import com.trunghieu.MyMusicApp.DTO.Request.LoginRequest;
import com.trunghieu.MyMusicApp.DTO.Request.User.LikeSongRequest;
import com.trunghieu.MyMusicApp.DTO.Request.User.UserCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.User.UserUpdateRequest;
import com.trunghieu.MyMusicApp.DTO.Response.*;
import com.trunghieu.MyMusicApp.Entity.Song;
import com.trunghieu.MyMusicApp.Entity.User;
import com.trunghieu.MyMusicApp.Mapper.UserMapper;
import com.trunghieu.MyMusicApp.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {
    UserRepository repository;
    UserMapper userMapper;

    public ApiResponse<LoginResponse> login(LoginRequest request){
        User user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("Không có người dùng"));
        LoginResponse loginResponse = new LoginResponse(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
        return new ApiResponse<>(200, "Đăng nhập thành công", loginResponse);
    }

    public ApiResponse<UserResponse> addUser(UserCreationRequest request){
        User user = userMapper.toUser(request);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(request.getPassword());
        user.setPassword(password);
        repository.save(user);
        return new ApiResponse<>(200,"Thêm người dùng thành công", userMapper.toUserResponse(user));
    }

    public ApiResponse<UserResponse> deleteUser(int id){
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng"));
        repository.delete(user);
        return new ApiResponse<>(200, "Xóa người dùng thành công", userMapper.toUserResponse(user));
    }

    public ApiResponse<UserResponse> updateUser(int id, UserUpdateRequest request){
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(request.getPassword());
        user.setPassword(password);
        repository.save(user);
        return new ApiResponse<>(200, "Đổi mật khẩu thành công", userMapper.toUserResponse(user));
    }

    //Chỉnh sửa lại
    public ApiResponse<List<LikedSongResponse>> likeSong(int id, LikeSongRequest request){
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng"));
        List<Song> songList = user.getLikedSongs();
        List<LikeSongRequest> likeSongRequests = userMapper.toLikeSongRequest(songList);
        likeSongRequests.add(request);
        List<Song> songs = userMapper.toSongList(likeSongRequests);
        user.setLikedSongs(songs);
        repository.save(user);
        List<LikedSongResponse> likedSongResponses = userMapper.toLikedSongResponse(songs);
        return new ApiResponse<>(200, "Like thành công", likedSongResponses);
    }

    public ApiResponse<List<LikedSongResponse>> stopLiking(int id, LikeSongRequest request){
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng"));
        List<Song> songList = user.getLikedSongs();

        songList.removeIf(song -> song.getId() == request.getId());
        repository.save(user);
        List<LikedSongResponse> likedSongResponseList = userMapper.toLikedSongResponse(songList);
        return new ApiResponse<>(200, "Bỏ thích thành công", likedSongResponseList);

    }
    public ApiResponse<List<UserResponse>> getUserList(){
        List<User> users = repository.findAll();
        List<UserResponse> userList = userMapper.toUserResponseList(users);
        return new ApiResponse<>(200,"Danh sách người dùng", userList);
    }
    public ApiResponse<List<LikedSongResponse>> getLikedSongList(int id){
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        List<LikedSongResponse> likedSongResponses = userMapper.toLikedSongResponse(user.getLikedSongs());
        return new ApiResponse<>(200, "danh sách các bài hát đã thích", likedSongResponses);
    }
}
