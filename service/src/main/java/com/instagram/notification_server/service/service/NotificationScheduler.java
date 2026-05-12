package com.instagram.notification_server.service.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NotificationScheduler {

    private final WebClient webClient;

    public NotificationScheduler(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://insta-microservice.onrender.com").build();
    }

    @Scheduled(fixedRate = 840000)
    public void callNotificationApi() {

        String response = webClient
                .get()
                .uri("/notification/get")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(response);
    }
}
