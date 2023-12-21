package com.musicfileutil.ui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"com.musicfileutil"})
public class UiApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UiApplication.class).headless(false)
                                                         .run(args);
    }

}
