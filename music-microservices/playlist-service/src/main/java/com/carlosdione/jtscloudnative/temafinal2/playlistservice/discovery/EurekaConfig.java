package com.carlosdione.jtscloudnative.temafinal2.playlistservice.discovery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaConfig {
    @Bean
    public EurekaOperations eurekaOperations(){
        return new EurekaOperations();
    }
}
