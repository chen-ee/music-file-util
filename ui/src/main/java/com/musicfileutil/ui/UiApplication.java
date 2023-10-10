package com.musicfileutil.ui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UiApplication {
    public UiApplication() {
        SwingArea.getInstance()
                 .initUI();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(UiApplication.class).headless(false)
                                                         .run(args);
    }

}
