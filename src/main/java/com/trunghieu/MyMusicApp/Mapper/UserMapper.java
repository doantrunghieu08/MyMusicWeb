package com.trunghieu.MyMusicApp.Mapper;

import com.trunghieu.MyMusicApp.DTO.Request.LoginRequest;
import com.trunghieu.MyMusicApp.DTO.Request.User.LikeSongRequest;
import com.trunghieu.MyMusicApp.DTO.Request.User.UserCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.LikedSongResponse;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.DTO.Response.UserResponse;
import com.trunghieu.MyMusicApp.Entity.Song;
import com.trunghieu.MyMusicApp.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public User toUser(UserCreationRequest request);

    @Mapping(source = "user.id", target = "userID")
    public UserResponse toUserResponse(User user);
    public UserResponse toUserResponseFromLoginRequest(LoginRequest request);


    public List<LikeSongRequest> toLikeSongRequest(List<Song> song);
    public List<Song> toSongList(List<LikeSongRequest> likeSongRequestList);


    public List<UserResponse> toUserResponseList(List<User> users);

    @Mapping(source = "song.id", target = "id")
    public List<LikedSongResponse> toLikedSongResponse(List<Song> song);
}

