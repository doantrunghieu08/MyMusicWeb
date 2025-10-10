package com.trunghieu.MyMusicApp.Mapper;

import com.trunghieu.MyMusicApp.DTO.Request.Genre.GenreCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.CountGenreResponse;
import com.trunghieu.MyMusicApp.DTO.Response.GenreResponse;
import com.trunghieu.MyMusicApp.Entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    public Genre toGenre(GenreCreationRequest request);

    @Mapping(target = "songs", source = "genre.songList")
    public GenreResponse toGenreResponse(Genre genre);
    public List<GenreResponse> toGenreResponseList(List<Genre> genreList);
    public GenreResponse toGenreResponseFromCountGenreResponse(CountGenreResponse  response);
}
