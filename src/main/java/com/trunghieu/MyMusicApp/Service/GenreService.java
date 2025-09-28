package com.trunghieu.MyMusicApp.Service;

import com.trunghieu.MyMusicApp.DTO.Request.Genre.GenreCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Genre.GenreUpdationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.CountGenreResponse;
import com.trunghieu.MyMusicApp.DTO.Response.GenreResponse;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Entity.Genre;
import com.trunghieu.MyMusicApp.Entity.Song;
import com.trunghieu.MyMusicApp.Mapper.GenreMapper;
import com.trunghieu.MyMusicApp.Mapper.SongMappper;
import com.trunghieu.MyMusicApp.Repository.GenreRepository;
import com.trunghieu.MyMusicApp.Repository.SongRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreService {
    GenreRepository repository;
    GenreMapper mapper;
    SongRepository songRepository;
    SongMappper songMappper;
    public ApiResponse<GenreResponse> addGenre(GenreCreationRequest request){
        Genre genre = mapper.toGenre(request);
        repository.save(genre);
        return new ApiResponse<>(200, "Thêm thành công thể loại", mapper.toGenreResponse(genre));
    }

    public ApiResponse<GenreResponse> deleteGenre(int id){
        Genre genre = repository.findById(id).orElseThrow(() -> new RuntimeException("Không có thể loại"));
        repository.deleteById(id);
        return new ApiResponse<>(200, "Xóa thể loại thành công", mapper.toGenreResponse(genre));
    }

    public ApiResponse<GenreResponse> getSongByGenre(int id){
        Genre genre = repository.findById(id).orElseThrow(() -> new RuntimeException("Không có thể loại"));
        return new ApiResponse<>(200, "Các bài hát theo thể loại", mapper.toGenreResponse(genre));

    }

    public ApiResponse<List<GenreResponse>> getGenreList(){
        List<Genre> genreList = repository.findAll();
        return new ApiResponse<>(200, "Danh sách thể loại", mapper.toGenreResponseList(genreList));
    }
    public ApiResponse<CountGenreResponse> countSongByGenre(int id){


        CountGenreResponse countGenreResponse = repository.countSongByGenre_id(id);

        return new ApiResponse<>(200, "Số lượng bài hát theo thể loại", countGenreResponse);
    }

    public ApiResponse<GenreResponse> updateGenre(int id, GenreUpdationRequest request){
        Genre genre = repository.findById(id).orElseThrow(() -> new RuntimeException("Không có thể loại"));
        genre.setName(request.getName());
        genre.setDescription(request.getDescription());
        List<Song> songList = songRepository.findAllById(request.getSongList());
        genre.setSongList(songList);
        repository.save(genre);
        return new ApiResponse<>(200, "Cập nhật thành công", mapper.toGenreResponse(genre));
    }
}
