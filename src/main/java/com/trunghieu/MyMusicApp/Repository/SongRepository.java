package com.trunghieu.MyMusicApp.Repository;

import com.trunghieu.MyMusicApp.DTO.Request.Song.SongAdditationRequest;
import com.trunghieu.MyMusicApp.DTO.Request.Song.SongUpdationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.SongResponse;
import com.trunghieu.MyMusicApp.Entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.net.http.HttpResponse;
import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    public List<Song> findByGenre_Id(int id);
    public List<Song> findByArtist_Id(int id);
    public List<Song> findByTitleContainingIgnoreCase(String keyword);
}
