package com.example.community.controller;

import com.example.community.entity.Community;
import com.example.community.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/communities")
@Slf4j
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
}
