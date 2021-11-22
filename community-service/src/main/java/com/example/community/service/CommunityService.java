package com.example.community.service;

import com.example.community.entity.Community;
import com.example.community.entity.Log;
import com.example.community.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Community> findAllCommunities() {
        LogRequest(1L, "GET", "Find all communities");
        return communityRepository.findAll();
    }

    public Optional<Community> findCommunityById(Long communityId) {
        LogRequest(1L, "GET", "Find community");
        return communityRepository.findById(communityId);
    }

    public Community saveCommunity(Community community) {
        LogRequest(1L, "POST", "Save community");
        return communityRepository.save(community);
    }

    public void updateCommunity(Community community) {
        LogRequest(1L, "PUT", "Update community");
        communityRepository.save(community);
    }

    public void deleteCommunity(Long communityId) {
        LogRequest(1L, "DELETE", "Delete community");
        communityRepository.deleteById(communityId);
    }

    private void LogRequest(Long userId, String action, String description) {
        Log log = new Log(userId, "Community-service", action, description);
        HttpEntity<Log> request = new HttpEntity<>(log);
        restTemplate.postForObject("http://logging-service/logs/saveLog", request, Log.class);
        System.out.println(log.toString());
    }
}
