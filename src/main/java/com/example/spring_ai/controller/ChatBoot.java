package com.example.spring_ai.controller;

import com.example.spring_ai.dto.ChatRequest;
import com.example.spring_ai.response.ChatResponse;
import com.example.spring_ai.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatBoot {

    private final ChatService  chatService;

    public ChatBoot(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> response(@RequestBody @Valid ChatRequest chatRequest) {
        ChatResponse chatResponse = chatService.createChat(chatRequest);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponse);
    }
}
