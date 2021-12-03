package com.example.community.entity.DTO;

import com.example.community.entity.Community;
import com.example.community.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommunity {

    private Long gameId;
    private List<User> users;

    public UserCommunity(Optional<Community> community, List<User> users) {
        this.gameId = community.get().getGameId();
        this.users = users;
    }
}
