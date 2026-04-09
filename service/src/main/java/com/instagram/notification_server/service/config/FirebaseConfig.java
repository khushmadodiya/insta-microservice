package com.instagram.notification_server.service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

//export FIREBASE_CONFIG="$(cat /Users/ent-00350/Documents/insta-firebase-admin.json)"

@Configuration
public class FirebaseConfig {
    @PostConstruct
    public void init() {
        try {

            String firebaseConfig = System.getenv("FIREBASE_CONFIG");

            InputStream serviceAccount =
                    new ByteArrayInputStream(firebaseConfig.getBytes());


            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }
}