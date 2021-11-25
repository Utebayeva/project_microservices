package com.example.library.controller;

import com.example.library.entity.DTO.UserLibrary;
import com.example.library.entity.Library;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("")
    public List<Library> findAllLibraries() {
        return libraryService.findAllLibraries();
    }

    @GetMapping("/{id}")
    public List<Library> findLibraryById(@PathVariable("id") Long userId) {
        return libraryService.findLibraryByUserId(userId);
    }

    @PostMapping("/saveLibrary")
    public void saveLibrary(@RequestBody Library library) {
        libraryService.saveLibrary(library);
    }

    @PutMapping("/updateLibrary")
    public void updateLibrary(@RequestBody Library library) {
        libraryService.updateLibrary(library);
    }

    @DeleteMapping("/{id}")
    public void deleteLibrary(@PathVariable("id") Long userId) {
        libraryService.deleteLibrary(userId);
    }

    @PostMapping("/addGame")
    public void addGameToLibrary(@RequestBody Library library) {
        libraryService.addGameToLibrary(library);
    }

    @GetMapping("/getUserLibraryById/{id}")
    public UserLibrary findLibraryGamesByUserId(@PathVariable("id") Long userId) {
        return libraryService.findLibraryGamesByUserId(userId);
    }
}
