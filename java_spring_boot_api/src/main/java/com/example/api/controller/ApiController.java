package com.example.api.controller;

import com.example.api.model.ChatRequest;
import com.example.api.model.ChatResponse;
import com.example.api.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ChatService chatService;

    @Autowired
    public ApiController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/ping")
    public ResponseEntity<Map<String, String>> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("durum", "tamamlandı");
        response.put("mesaj", "sistem çalışıyor");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/chat")
    public Mono<ResponseEntity<ChatResponse>> chat(@RequestBody ChatRequest request) {
        return chatService.sendChatRequest(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
} 