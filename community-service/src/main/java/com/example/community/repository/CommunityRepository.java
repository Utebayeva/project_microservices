package com.example.community.repository;

import com.example.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    Community findCommunityByCommunityId(Long communityId);

    Community deleteCommunityByCommunityId(Long communityId);
}
