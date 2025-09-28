package com.trunghieu.MyMusicApp.Mapper;

import com.trunghieu.MyMusicApp.DTO.Request.Artist.ArtistCreationRequest;
import com.trunghieu.MyMusicApp.DTO.Response.ArtistResponse;
import com.trunghieu.MyMusicApp.Entity.Artist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    public ArtistResponse toResponse(Artist artist);
    public Artist toArtist (ArtistCreationRequest request);
    public List<ArtistResponse> toArtistList(List<Artist> artists);
}
