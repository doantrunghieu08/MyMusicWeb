package com.trunghieu.MyMusicApp.Service;

import com.trunghieu.MyMusicApp.DTO.Request.Artist.ArtistCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Artist.ArtistUpdataionRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.ArtistResponse;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Entity.Artist;
import com.trunghieu.MyMusicApp.Mapper.ArtistMapper;
import com.trunghieu.MyMusicApp.Mapper.SongMappper;
import com.trunghieu.MyMusicApp.Repository.ArtistRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ArtistService {
    ArtistRepository repository;
    SongMappper mappper;
    ArtistMapper artistMapper;
    public ApiResponse<List<SongResponse>> getsongList(int id){
        Optional<Artist> artist = repository.findById(id);
        if(artist.isPresent()){
            List<SongResponse> songResponseList = mappper.toSongResponseList(artist.get().getSongsList());
            return new ApiResponse<>(200, "Danh sách bài hát của ca sĩ đã tìm", songResponseList);
        }
        return new ApiResponse<>(404, "Không có bài hát nào", null);
    }
    public ApiResponse<ArtistResponse> addArtist(ArtistCreationRequest request){
        Artist artist = artistMapper.toArtist(request);
        repository.save(artist);
        return new ApiResponse<>(200, "Thêm thành công", artistMapper.toResponse(artist));
    }
    public ApiResponse<ArtistResponse> delArtist(int id){
        Optional<Artist> artistOptional = repository.findById(id);
        if(artistOptional.isPresent()){
            repository.deleteById(id);
            return new ApiResponse<>(200, "Xóa thành công", artistMapper.toResponse(artistOptional.get()));
        }
        return new ApiResponse<>(404, "Không tìm thấy ca sĩ", null);
    }

    public ApiResponse<ArtistResponse> updateArtist(int id, ArtistUpdataionRequest request){
        Optional<Artist> artistOptional = repository.findById(id);
        if(artistOptional.isPresent()){
            Artist artist = artistOptional.get();
            artist.setName(request.getName());
            artist.setBio(request.getBio());
            artist.setImage_url(request.getImage_url());

            repository.save(artist);
            return new ApiResponse<>(200, "Cập nhật thành công", artistMapper.toResponse(artist));
        }
        return new ApiResponse<>(404, "Không có ca sĩ cần tìm", null);
    }

    public ApiResponse<List<ArtistResponse>> getArtistList(){
        List<Artist> artists = repository.findAll();
        if(!artists.isEmpty()){
            return new ApiResponse<>(200, "Danh sách ca sĩ: ", artistMapper.toArtistList(artists));
        }
        return new ApiResponse<>(404, "Không có ca sĩ", null);
    }
    public ApiResponse<List<ArtistResponse>> searchArtist(String keyword){
        List<Artist> artistList = repository.searchByName(keyword);
        if(!artistList.isEmpty()){
            return new ApiResponse<>(200, "Các ca sĩ", artistMapper.toArtistList(artistList));
        }
        return new ApiResponse<>(404, "Không có ca sĩ", null);
    }
}
