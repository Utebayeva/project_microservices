package com.example.community.controller;

import com.example.community.entity.Community;
import com.example.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communities")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @GetMapping("")
    public List<Community> findAllCommunities() {
        return communityService.findAllCommunities();
    }

    @GetMapping("/{id}")
    public Community findCommunityById(@PathVariable("id") Long communityId) {
        return communityService.findCommunityById(communityId);
    }

    @PostMapping("/saveUser")
    public Community saveCommunity(@RequestBody Community community) {
        return communityService.saveCommunity(community);
    }

    @DeleteMapping("/{id}")
    public Community deleteCommunity(@PathVariable("id") Long communityId) {
        return communityService.deleteCommunity(communityId);
    }
}
