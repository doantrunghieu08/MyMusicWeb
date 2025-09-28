package com.trunghieu.MyMusicApp.Repository;

import com.trunghieu.MyMusicApp.Entity.Playlist;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
//    @Query(value = "SELECT * FROM playlists WHERE playlists.user_id = keyword", nativeQuery = true)
    public List<Playlist> findAllByUserId( int user_id);
}
