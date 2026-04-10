package com.instagram.notification_server.service.service;

import com.google.firebase.messaging.*;
import com.instagram.notification_server.service.FirebaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final FirebaseService firebaseService;

    public NotificationService(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

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

        BatchResponse response = FirebaseMessaging.getInstance()
                .sendEachForMulticast(message);
        List<SendResponse> responses = response.getResponses();

        for (int i = 0; i < responses.size(); i++) {

            SendResponse sendResponse = responses.get(i);

            if (!sendResponse.isSuccessful()) {

                FirebaseMessagingException exception = sendResponse.getException();

                if (exception.getMessagingErrorCode() == MessagingErrorCode.UNREGISTERED
                        || exception.getMessagingErrorCode() == MessagingErrorCode.INVALID_ARGUMENT) {

                    String invalidToken = tokens.get(i);

                   firebaseService.deleteTokenFromFirestore(invalidToken);
                }
            }
        }

        return FirebaseMessaging.getInstance().sendEachForMulticast(message);
    }
}