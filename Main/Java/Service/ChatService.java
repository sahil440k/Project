package com.example.chat.service;

import com.example.chat.model.Message;
import com.example.chat.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final List<Message> chatLog = new ArrayList<>();
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final String JWT_SECRET = "your_jwt_secret_key_here";

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public ResponseEntity<?> register(User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username and password are required"));
        }

        if (users.containsKey(user.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
        }

        user.setId(String.valueOf(users.size() + 1));
        users.put(user.getUsername(), user);

        String token = generateToken(user);
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("token", token);

        return ResponseEntity.status(201).body(response);
    }

    public ResponseEntity<?> login(User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username and password are required"));
        }

        User existingUser = users.get(user.getUsername());
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }

        String token = generateToken(existingUser);
        Map<String, Object> response = new HashMap<>();
        response.put("user", existingUser);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    public List<Message> getMessages(String roomId) {
        return chatLog;
    }

    public void handleMessage(Message message) {
        logger.info("Handling message in service: {}", message);
        if (message.getTimestamp() == null) {
            message.setTimestamp(LocalDateTime.now());
        }
        
        // Validate image messages
        if ("image".equals(message.getType()) && message.getImageData() != null) {
            // Check if the image data is a valid base64 string
            if (!message.getImageData().startsWith("data:image/")) {
                logger.error("Invalid image data received");
                return;
            }
            
            // Optionally limit image size
            if (message.getImageData().length() > 5 * 1024 * 1024) { // 5MB limit
                logger.error("Image size too large");
                return;
            }
        }
        
        chatLog.add(message);
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setId(user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(key)
                .compact();
    }
} 
