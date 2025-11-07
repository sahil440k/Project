package com.example.chat.controller;

import com.example.chat.model.Message;
import com.example.chat.model.User;
import com.example.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return chatService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return chatService.login(user);
    }

    @GetMapping("/messages/{roomId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId) {
        return ResponseEntity.ok(chatService.getMessages(roomId));
    }
} 
