package com.instagram.notification_server.service.service;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    public String sendNotification(String token, String title, String body) throws Exception {

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        return FirebaseMessaging.getInstance().send(message);
    }

    public BatchResponse sendNotifications(List<String> tokens, String title, String body) throws Exception {

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(tokens)
                .setNotification(notification)
                .build();

        return FirebaseMessaging.getInstance().sendEachForMulticast(message);
    }
}