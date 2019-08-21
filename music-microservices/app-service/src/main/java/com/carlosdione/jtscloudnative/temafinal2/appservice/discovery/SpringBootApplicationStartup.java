package com.carlosdione.jtscloudnative.temafinal2.appservice.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class SpringBootApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private EurekaOperations eurekaOperations;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event){
        try {
            eurekaOperations.setServerPort(event.getApplicationContext().getEnvironment().getProperty("server.port"));
            eurekaOperations.setIpAddress(InetAddress.getLocalHost().getHostAddress());
            eurekaOperations.register();
        }catch (UnknownHostException e){
            e.getMessage();
        }
    }
}
