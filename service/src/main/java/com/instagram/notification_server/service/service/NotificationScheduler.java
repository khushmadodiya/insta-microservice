package com.instagram.notification_server.service.service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.google.api.client.util.Data.mapOf;

@Component
public class NotificationScheduler {
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void callNotificationApi() {
        Firestore db = FirestoreClient.getFirestore();
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        try {

            String response = restTemplate.getForObject("https://insta-microservice.onrender.com/notification/get", String.class);
            db.collection("Notification").document("ping").set(Map.of("log", "scheduler called at" + currentTime));
            System.out.println("Response = " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
