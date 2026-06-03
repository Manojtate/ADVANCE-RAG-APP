package com.spring.ai.AdanceRagFirstProject.first_project.controller;

import com.spring.ai.AdanceRagFirstProject.first_project.services.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

    private ChatService chatService;

    public ChatController(ChatService chatService) {

        this.chatService = chatService;
    }

    @GetMapping("/user/advanceRag/search")
    public ResponseEntity<String> getReponseUsingAdvanceRag(
            @RequestParam(value = "q", required = true) String query) {

        return ResponseEntity.ok(this.chatService.getResponse(query));

    }

}
