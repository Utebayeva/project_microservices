package com.example.community.service;

import com.example.community.entity.Community;
import com.example.community.repository.CommunityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    public List<Community> findAllCommunities() {
        return communityRepository.findAll();
    }

    public Community findCommunityById(Long communityId) {
        return communityRepository.findCommunityByCommunityId(communityId);
    }
}
