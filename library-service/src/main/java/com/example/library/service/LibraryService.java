package com.example.library.service;

import com.example.library.entity.DTO.UserLibrary;
import com.example.library.entity.Game;
import com.example.library.entity.Library;
import com.example.library.entity.Log;
import com.example.library.entity.User;
import com.example.library.repository.LibraryRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Library> findAllLibraries() {
        LogRequest(1L, "GET", "Find all libraries");
        return libraryRepository.findAll();
    }

    public List<Library> findLibraryByUserId(Long userId) {
        LogRequest(1L, "GET", "Find library");
        return libraryRepository.findLibraryByUserId(userId);
    }

    public void saveLibrary(Library library) {
        User user = restTemplate.getForObject("http://user-service/users/" + Long.toString(library.getUserId()), User.class);
        Game game = restTemplate.getForObject("http://game-service/games/" + Long.toString(library.getGameId()), Game.class);
        if (user != null && game != null) {
            libraryRepository.save(library);
            LogRequest(1L, "POST", "Save library");
        } else {
            LogRequest(1L, "POST", "Not saved library");
        }
    }

    public void updateLibrary(Library library) {
        LogRequest(1L, "PUT", "Update library");
        libraryRepository.save(library);
    }

    public void deleteLibrary(Long userId) {
        LogRequest(1L, "DELETE", "Delete library");
        libraryRepository.deleteLibraryByUserId(userId);
    }

    private void LogRequest(Long userId, String action, String description) {
        Log log = new Log(userId, "Library-service", action, description);
        HttpEntity<Log> request = new HttpEntity<>(log);
        restTemplate.postForObject("http://logging-service/logs/saveLog", request, Log.class);
        System.out.println(log.toString());
    }

    public void addGameToLibrary(Library library) {
        User user = restTemplate.getForObject("http://user-service/users/" + Long.toString(library.getUserId()), User.class);
        if (user != null) {
            Game game = restTemplate.getForObject("http://game-service/games/" + library.getGameId(), Game.class);
            if (game != null) {
                library.setGameId(game.getGameId());
                libraryRepository.save(library);
                LogRequest(1L, "POST", "Game added to library");
                System.out.println("Game added");
            }
            else {
                LogRequest(1L, "POST", "Game didn't add to library");
                System.out.println("Not found game");
            }
        }
        else {
            LogRequest(1L, "POST", "Game didn't add to library");
            System.out.println("Not found library");
        }
    }

    @HystrixCommand(
            fallbackMethod = "findLibraryGamesByUserIdFallback",
            threadPoolKey = "findLibraryGamesByUserId"
    )
    public UserLibrary findLibraryGamesByUserId(Long userId) {
        List<Library> libraries = libraryRepository.findLibraryByUserId(userId);
        UserLibrary userLibrary = new UserLibrary(userId, new ArrayList<>());
        User user = restTemplate.getForObject("http://user-service/users/" + userId, User.class);
        if (user != null) {
            for (Library value : libraries) {
                Game game = restTemplate.getForObject("http://game-service/games/" + Long.toString(value.getGameId()), Game.class);
                if (game != null && value.getUserId().equals(userId) && game.getGameId().equals(value.getGameId())) {
                    userLibrary.setUserId(userId);
                    userLibrary.getGames().add(game);
                }
            }
            if (userLibrary.getGames() == null) {
                LogRequest(1L, "GET", "Not found any games");
            }
            LogRequest(1L, "GET", "Founded games of this library");
        }
        else {
            LogRequest(1L, "GET", "Not found library");
        }
        return userLibrary;
    }

    public UserLibrary findLibraryGamesByUserIdFallback(Long userId) {
        UserLibrary userLibrary = new UserLibrary(0L, new ArrayList<>());
        Game game = new Game(0L, "Game-service not available", 0.0, "Game-service not available");
        userLibrary.setUserId(0L);
        userLibrary.getGames().add(game);
        LogRequest(1L, "GET", "Not found library");
        return userLibrary;
    }
}
