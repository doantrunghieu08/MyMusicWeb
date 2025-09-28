package com.trunghieu.MyMusicApp.Repository;

import com.trunghieu.MyMusicApp.DTO.Response.CountGenreResponse;
import com.trunghieu.MyMusicApp.Entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query("SELECT new com.trunghieu.MyMusicApp.DTO.Response.CountGenreResponse(" +
            "g.id, g.name, COUNT(s.id)) " +
            "FROM Song s JOIN s.genre g " +
            "WHERE g.id = :genre_id " +
            "GROUP BY g.id, g.name")
    public CountGenreResponse countSongByGenre_id(@Param("genre_id") int genre_id);


}
