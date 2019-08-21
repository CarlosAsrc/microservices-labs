package com.carlosdione.jtscloudnative.temafinal2.appservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppServiceApplication.class, args);
    }

}
