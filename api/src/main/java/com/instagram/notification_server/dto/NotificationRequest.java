package com.instagram.notification_server.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotificationRequest {

    private List<String> token;
    private String title;
    private String body;

}