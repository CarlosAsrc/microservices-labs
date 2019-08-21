package com.carlosdione.jtscloudnative.temafinal2.appservice.discovery;

import feign.Feign;
import feign.gson.GsonDecoder;

import org.springframework.beans.factory.annotation.Value;

public class EurekaOperations {

    private String serverPort;
    private String ipAddress;

    @Value("${host.name}")
    private String hostName;

    @Value("${service.name}")
    private String serviceName;

    @Value("${eureka.apps}")
    private String eurekaApps;
    
    @Value("${secure.port}")
    private String securePort;
    
    @Value("${eureka.healthCheckUrl}")
    private String healthCheckUrl;
    
    @Value("${eureka.statusPageUrl}")
    private String statusPageUrl;
    
    @Value("${eureka.homePageUrl}")
    private String homePageUrl;

    private EurekaHttpMethods eurekaHttpMethodsService;

    public void register(){
        this.eurekaHttpMethodsService = Feign.builder().decoder(new GsonDecoder()).target(EurekaHttpMethods.class, eurekaApps);
        eurekaHttpMethodsService.registry("{\n" +
                "    \"instance\": {\n" +
                "        \"hostName\": \""+ hostName +"\",\n" +
                "        \"app\": \""+ serviceName +"\",\n" +
                "        \"vipAddress\": \""+serviceName+"\",\n" +
                "        \"secureVipAddress\": \"com.teste\",\n" +
                "        \"ipAddr\": \""+ipAddress+"\",\n" +
                "        \"status\": \"STARTING\",\n" +
                "        \"port\": {\"$\": \"" + serverPort + "\", \"@enabled\": \"true\"},\n" +
                "        \"securePort\": {\"$\": \""+securePort+"\", \"@enabled\": \"true\"},\n" +
                "        \"healthCheckUrl\": \""+healthCheckUrl+"\",\n" +
                "        \"statusPageUrl\": \""+statusPageUrl+"\",\n" +
                "        \"homePageUrl\": \""+homePageUrl+"\",\n" +
                "        \"dataCenterInfo\": {\n" +
                "            \"@class\": \"com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo\",\n" +
                "            \"name\": \"MyOwn\"\n" +
                "        }\n" +
                "    }\n" +
                "}", serviceName);
        eurekaHttpMethodsService.updateToUP(serviceName, hostName);
    }

    public void delete(){
        eurekaHttpMethodsService.delete(serviceName, hostName);
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
