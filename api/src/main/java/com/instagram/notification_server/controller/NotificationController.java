package com.instagram.notification_server.controller;

import com.instagram.notification_server.dto.NotificationRequest;
import com.instagram.notification_server.service.service.NotificationService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public String sendNotification(
            @RequestParam String token,
            @RequestParam String title,
            @RequestParam String body
    ) throws Exception {

        return notificationService.sendNotification(token, title, body);
    }

    @GetMapping("/get")
    public String  get(){
        return "Hello World!";
    }
}