package com.carlosdione.jtscloudnative.temafinal2.appservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carlosdione.jtscloudnative.temafinal2.appservice.AppService;

@Configuration
public class AppConfig {

	@Bean
    public AppService appService() {
        return new AppService();
    }
}
