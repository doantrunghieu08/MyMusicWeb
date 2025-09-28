package com.trunghieu.MyMusicApp.Repository;

import com.trunghieu.MyMusicApp.Entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    public List<Artist> findByNameContainingIgnoreCase(String keyword);

    @Query(value = "SELECT *FROM artists a WHERE a.name COLLATE utf8mb4_unicode_ci LIKE %:keyword%", nativeQuery = true)
    public List<Artist> searchByName(@Param("keyword") String keyword);
}
