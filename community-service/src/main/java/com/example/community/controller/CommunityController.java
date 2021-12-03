package com.example.community.controller;

import com.example.community.entity.Community;
import com.example.community.entity.DTO.UserCommunity;
import com.example.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communities")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("")
    public List<Community> findAllCommunities() {
        return communityService.findAllCommunities();
    }

    @GetMapping("/{id}")
    public List<Community> findCommunityByGameId(@PathVariable("id") Long gameId) {
        return communityService.findCommunityByGameId(gameId);
    }

    @PostMapping("/saveCommunity")
    public void saveCommunity(@RequestBody Community community) {
        communityService.saveCommunity(community);
    }

    @PutMapping("/updateCommunity")
    public void updateCommunity(@RequestBody Community community) {
        communityService.updateCommunity(community);
    }

    @DeleteMapping("/{id}")
    public void deleteCommunityByGameId(@PathVariable("id") Long gameId) {
        communityService.deleteCommunityByGameId(gameId);
    }

    @PostMapping("/addGame")
    public void addUserToCommunity(@RequestBody Community community) {
        communityService.addUserToCommunity(community);
    }

    @GetMapping("/findCommunityUsersByGameId/{id}")
    public UserCommunity findCommunityUsersByGameId(@PathVariable("id") Long gameId) {
        return communityService.findCommunityUsersByGameId(gameId);
    }
}
