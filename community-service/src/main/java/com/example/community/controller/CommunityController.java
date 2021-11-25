package com.example.community.controller;

import com.example.community.entity.Community;
import com.example.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Community> findCommunityById(@PathVariable("id") Long communityId) {
        return communityService.findCommunityById(communityId);
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
    public void deleteCommunity(@PathVariable("id") Long communityId) {
        communityService.deleteCommunity(communityId);
    }
}
