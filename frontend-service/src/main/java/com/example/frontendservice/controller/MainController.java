package com.example.frontendservice.controller;

import com.example.frontendservice.entity.Community;
import com.example.frontendservice.entity.DTO.*;
import com.example.frontendservice.entity.Game;
import com.example.frontendservice.entity.Library;
import com.example.frontendservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/store")
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("")
    public String index(Model model) {
        GamesArrayList gamesArrayList = restTemplate.getForObject("http://zuul-service/games/gamesArrayList", GamesArrayList.class);
        model.addAttribute("games", gamesArrayList.getGames());
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        GamesArrayList gamesArrayList = restTemplate.getForObject("http://zuul-service/games/gamesArrayList", GamesArrayList.class);
        model.addAttribute("games", gamesArrayList.getGames());
        return "home";
    }

    @GetMapping("/communities")
    public String communities(Model model) {
        CommunitiesArrayList communitiesArrayList = restTemplate.getForObject("http://zuul-service/communities/communitiesArrayList", CommunitiesArrayList.class);
        model.addAttribute("communities", communitiesArrayList.getCommunities());
        return "communities";
    }

    @GetMapping("/createGame")
    public String createGame() {
        return "createGame";
    }

    @PostMapping("/createGame")
    public String createGame(@ModelAttribute Game game) {
        HttpEntity<Game> request = new HttpEntity<>(game);
        restTemplate.postForObject("http://zuul-service/games/saveGame", request, Game.class);
        return "redirect:/store/admin";
    }

    @GetMapping("/createCommunity")
    public String createCommunity() {
        return "createCommunity";
    }

    @PostMapping("/createCommunity")
    public String createCommunity(@ModelAttribute Community community) {
        HttpEntity<Community> request = new HttpEntity<>(community);
        restTemplate.postForObject("http://zuul-service/communities/saveCommunity", request, Community.class);
        return "redirect:/store/communities";
    }

    @PostMapping("/deleteGame")
    private String deleteGame(Long id) {
        restTemplate.delete("http://zuul-service/games/" + id, Game.class);
        return "redirect:/store/admin";
    }

    @PostMapping("/deleteUser")
    private String deleteUser(Long id) {
        restTemplate.delete("http://zuul-service/users/" + id, User.class);
        return "redirect:/store/admin";
    }

    @PostMapping("/deleteCommunity")
    private String deleteCommunity(Long id) {
        restTemplate.delete("http://zuul-service/communities/" + id, User.class);
        return "redirect:/store/admin";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        LogsArrayList logsArrayList = restTemplate.getForObject("http://zuul-service/logs/logsArrayList", LogsArrayList.class);
        GamesArrayList gamesArrayList = restTemplate.getForObject("http://zuul-service/games/gamesArrayList", GamesArrayList.class);
        UsersArrayList usersArrayList = restTemplate.getForObject("http://zuul-service/users/usersArrayList", UsersArrayList.class);
        CommunitiesArrayList communitiesArrayList = restTemplate.getForObject("http://zuul-service/communities/communitiesArrayList", CommunitiesArrayList.class);
        model.addAttribute("logs", logsArrayList.getLogs());
        model.addAttribute("games", gamesArrayList.getGames());
        model.addAttribute("users", usersArrayList.getUsers());
        model.addAttribute("communities", communitiesArrayList.getCommunities());
        return "admin";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        GamesArrayList gamesArrayList = restTemplate.getForObject("http://zuul-service/games/gamesArrayList", GamesArrayList.class);
        model.addAttribute("games", gamesArrayList.getGames());
        return "profile";
    }

    @GetMapping("/signinPage")
    public String signIn() {
        return "signin";
    }

    @PostMapping("/signin")
    public String signIn(Model model, String nickname, String password, HttpSession session) {
        User user = restTemplate.getForObject("http://zuul-service/users/nickname/" + nickname, User.class);
        if (user != null && user.getNickname().equals("admin") && user.getPassword().equals("admin")) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("nickname", user.getNickname());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("wallet", user.getWallet());
            model.addAttribute("nickname", user);
            model.addAttribute("password", password);
            return "redirect:/store/admin";
        } else if (user != null && !user.getNickname().equals("admin") && user.getPassword().equals(password)) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("nickname", user.getNickname());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("wallet", user.getWallet());
            model.addAttribute("nickname", user);
            model.addAttribute("password", password);
            return "redirect:/store/home";
        } else {
            return "redirect:/store";
        }
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(User user) {
        HttpEntity<User> request = new HttpEntity<>(user);
        restTemplate.postForObject("http://zuul-service/users/saveUser", request, User.class);
        return "redirect:/store/signinPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/store";
    }
}
