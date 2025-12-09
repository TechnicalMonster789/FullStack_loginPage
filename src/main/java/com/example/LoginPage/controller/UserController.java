package com.example.LoginPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LoginPage.model.User;
import com.example.LoginPage.repository.UserRepository;

@Controller
public class UserController {	

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";     // looks for login.html in templates
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        // 🔹 1. Check if email already exists
        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email already registered!");
            return "login";    // show same page again with error
        }

        // 🔹 2. Save new user if not duplicate
        User user = new User(name, email, password);
        userRepository.save(user);

        model.addAttribute("message", "User registered successfully!");
        return "login";
    }

}
