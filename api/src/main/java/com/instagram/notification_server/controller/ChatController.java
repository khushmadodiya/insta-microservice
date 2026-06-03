package com.instagram.notification_server.controller;

import com.instagram.notification_server.dto.TypingEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/typing")
    public void typing(TypingEvent event) {

        messagingTemplate.convertAndSend(
                "/topic/typing/" + event.getReceiverId(),
                event
        );
    }
}