package com.example.library.entity.DTO;

import com.example.library.entity.Game;
import com.example.library.entity.Library;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLibrary{

    private Long userId;
    private List<Game> games;

    public UserLibrary(Optional<Library> library, List<Game> games) {
        this.userId = library.get().getUserId();
        this.games = games;
    }
}
