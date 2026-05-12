package com.instagram.notification_server.service.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationScheduler {
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 10 * 60 * 5000)
    public void callNotificationApi() {

        try {

            String response = restTemplate.getForObject(
                    "https://insta-microservice.onrender.com/notification/get",
                    String.class
            );

            System.out.println("Response = " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
