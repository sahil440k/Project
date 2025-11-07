package com.example.chat.controller;

import com.example.chat.model.Message;
import com.example.chat.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(ChatWebSocketController.class);

    @Autowired
    private ChatService chatService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message handleMessage(Message message) {
        logger.info("Received message: {}", message);
        chatService.handleMessage(message);
        logger.info("Broadcasting message: {}", message);
        return message;
    }
} 
