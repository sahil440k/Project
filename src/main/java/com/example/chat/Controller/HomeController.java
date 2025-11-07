package com.example.chat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // When the user visits "/", Spring Boot loads index.html
    @GetMapping("/")
    public String home() {
        return "index"; // refers to src/main/resources/templates/index.html
    }
}

