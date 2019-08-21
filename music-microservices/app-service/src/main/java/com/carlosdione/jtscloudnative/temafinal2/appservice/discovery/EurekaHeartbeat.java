package com.carlosdione.jtscloudnative.temafinal2.appservice.discovery;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EurekaHeartbeat {

    @Value("${host.name}")
    private String hostName;

    @Value("${service.name}")
    private String serviceName;
    
    @Value("${eureka.apps}")
    private String eurekaApps;

    private EurekaHttpMethods eurekaHttpMethodsService;

    @Scheduled(fixedRate = 20000)
    public void heartBeat(){
        this.eurekaHttpMethodsService = Feign.builder().decoder(new GsonDecoder()).target(EurekaHttpMethods.class, eurekaApps);
        eurekaHttpMethodsService.heartBeat(serviceName, hostName);
    }
}
