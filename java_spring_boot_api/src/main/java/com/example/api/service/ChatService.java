package com.example.api.service;

import com.example.api.model.ChatRequest;
import com.example.api.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
public class ChatService {

    private final WebClient.Builder webClientBuilder;
    
    @Value("${llama.api.url:http://localhost:5000/chat}")
    private String CHAT_API_URL;
    
    @Autowired
    public ChatService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
    
    @PostConstruct
    public void init() {
        checkModelService();
    }
    
    private void checkModelService() {
        try {
            System.out.println("LLaMA servisi kontrol ediliyor...");
        
            WebClient webClient = webClientBuilder.build();
            ChatRequest testRequest = new ChatRequest("test");
            try {
                webClient.post()
                    .uri(CHAT_API_URL)
                    .header("X-Internal-Call", "spring-boot")
                    .bodyValue(testRequest)
                    .retrieve()
                    .toBodilessEntity()

                    .block();
                
                System.out.println("LLaMA servisi çalışıyor ve erişilebilir");
            } catch (Exception e) {
                System.err.println("Python servisine erişilemedi: " + e.getMessage());
                System.err.println("Python servisini başlatmak için: cd llama && python app.py");
            }
        } catch (Exception e) {
            System.err.println("Servis kontrolü sırasında hata oluştu: " + e.getMessage());
        }
    }

    public Mono<ChatResponse> sendChatRequest(ChatRequest request) {
        return webClientBuilder.build()
                .post()
                .uri(CHAT_API_URL)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .onErrorResume(e -> {
                    String errorMsg = "Python servisine bağlantı hatası: " + e.getMessage();
                    System.err.println(errorMsg);
                    return Mono.just(new ChatResponse(errorMsg + 
                                   ". Python servisini başlatın."));
                });
    }
} 