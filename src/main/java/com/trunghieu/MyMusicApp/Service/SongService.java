package com.trunghieu.MyMusicApp.Service;

import com.trunghieu.MyMusicApp.DTO.Request.Song.SongAdditationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Song.SongUpdationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ApiResponse;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Entity.Artist;
import com.trunghieu.MyMusicApp.Entity.Genre;
import com.trunghieu.MyMusicApp.Entity.Song;
import com.trunghieu.MyMusicApp.Mapper.SongMappper;
import com.trunghieu.MyMusicApp.Repository.ArtistRepository;
import com.trunghieu.MyMusicApp.Repository.GenreRepository;
import com.trunghieu.MyMusicApp.Repository.SongRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SongService {
    SongRepository repository;
    SongMappper mappper;
    ArtistRepository artistRepository;
    GenreRepository genreRepository;

    public ApiResponse<List<SongResponse>> getMusicList(){
        List<Song> songResponseList = repository.findAll();

        return new ApiResponse<>(200, "Danh sách nhạc", mappper.toSongResponseList(songResponseList));
    }

    public ApiResponse<SongResponse> addMusic(SongAdditationRequest request){
        Song song = mappper.toSong(request);
        Artist artist = artistRepository.findById(request.getArtistID()).orElseThrow(() -> new RuntimeException("Không có nghệ sĩ"));
        song.setArtist(artist);
        Genre genre = genreRepository.findById(request.getGenreID()).orElseThrow(() -> new RuntimeException("Không có thể loại"));
        song.setGenre(genre);
        repository.save(song);
        return new ApiResponse<>(200, "Thêm thành công bài hát", mappper.toSongResponseFromSong(song));
    }

    public ApiResponse<SongResponse> deleteSong(int id){
        if(repository.existsById(id)){
            Song song = repository.findById(id).get();
            repository.deleteById(id);

            return new ApiResponse<>(200, "Xóa thành công", mappper.toSongResponseFromSong(song));

        }
        return new ApiResponse<>(401, "Không có bài hát ", null);
    }
    public ApiResponse<SongResponse> updateSong(int id, SongUpdationRequest request){
        if(repository.existsById(id)){
            Song song = repository.findById(id).get();
            song.setTitle(request.getTitle());
            song.setDuration(request.getDuration());
            song.setFile_url(request.getFile_url());
            song.setImage_url(request.getImage_url());
            Artist artist = new Artist();
            artist.setId(request.getArtistID());
            song.setArtist(artist);

            Genre genre = new Genre();
            genre.setId(request.getGenreID());
            song.setGenre(genre);

            repository.save(song);

            return new ApiResponse<>(200, "Sửa thành công", mappper.toSongResponseFromSong(song));
        }
        return new ApiResponse<>(401, "Không tìm thấy bài hát", null);
    }
    public ApiResponse<SongResponse> searchSongByID(int id){
        Optional<Song> song = repository.findById(id);
        if(song.isPresent()){

            return new ApiResponse<>(200, "Bài hát cần tìm", mappper.toSongResponseFromSong(song.get()));
        }
        return new ApiResponse<>(404, "Không có bài hát cần tìm", null);
    }


    public ApiResponse<List<SongResponse>> searchSong(String keyword){
        List<Song> songList = repository.findByTitleContainingIgnoreCase(keyword);
        if(songList.isEmpty()){
            return new ApiResponse<>(404, "Không có bài hát", null);
        }
        return new ApiResponse<>(200, "Các bài hát", mappper.toSongResponseList(songList));
    }
}
