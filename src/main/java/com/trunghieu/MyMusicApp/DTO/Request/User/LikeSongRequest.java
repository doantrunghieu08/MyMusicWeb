package com.trunghieu.MyMusicApp.DTO.Request.User;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeSongRequest {
    int id;
}
