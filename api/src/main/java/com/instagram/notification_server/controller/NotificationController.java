package com.instagram.notification_server.controller;

import com.google.protobuf.Any;
import com.instagram.notification_server.dto.NotificationRequest;
import com.instagram.notification_server.service.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/")
    public String sendNotification(
            @RequestParam String token,
            @RequestParam String title,
            @RequestParam String body
    ) throws Exception {

        return notificationService.sendNotification(token, title, body);
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationRequest request) throws Exception{

        System.out.println(request.getTitle());
        System.out.println(request.getBody());
        System.out.println(request.getToken());
        List<String> tokens = request.getToken();
        String title = request.getTitle();
        String body = request.getBody();

        return notificationService.sendNotifications(tokens,title,body).toString();
    }

    @GetMapping("/get")
    public String  get(){
        return "Hello World!";
    }
}