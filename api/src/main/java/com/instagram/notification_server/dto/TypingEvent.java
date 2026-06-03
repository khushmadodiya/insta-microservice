package com.instagram.notification_server.dto;

import lombok.Data;
import lombok.val;

@Data
public class TypingEvent {

    private String senderId;
    private String receiverId;
    private Boolean isTyping;

}