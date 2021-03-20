package com.onemedics.pushapi.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Slf4j
public class FcmConfig {

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options =
                    new FirebaseOptions.Builder()
                            .setCredentials(
                                    GoogleCredentials.fromStream(
                                            new ClassPathResource(firebaseConfigPath)
                                                    .getInputStream()))
                            .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.error("FireBase Init Exception: ", e);
        }
    }
}
