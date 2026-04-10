package com.instagram.notification_server.service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirebaseService {

    public void deleteTokenFromFirestore(String token) throws Exception {

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future =
                db.collection("device_tokens")
                        .whereEqualTo("fcmToken", token)
                        .get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot doc : documents) {
            doc.getReference().delete();
        }
    }

}
