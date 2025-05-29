package org.container.platform.web.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class KpsPublicImageHubUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KpsPublicImageHubUiApplication.class, args);
    }

}
