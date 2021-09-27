package com.example.community.service;

import com.example.community.entity.Community;
import com.example.community.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    public List<Community> findAllCommunities() {
        return communityRepository.findAll();
    }

    public Community findCommunityById(Long communityId) {
        return communityRepository.findCommunityByCommunityId(communityId);
    }

    public Community saveCommunity(Community community) {
        return communityRepository.save(community);
    }


    public Community deleteCommunity(Long communityId) {
        return communityRepository.deleteCommunityByCommunityId(communityId);
    }
}
