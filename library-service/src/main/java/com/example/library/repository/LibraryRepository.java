package com.example.library.repository;

import com.example.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    List<Library> findLibraryByUserId(Long userId);
    void deleteLibraryByUserId(Long userId);
}
