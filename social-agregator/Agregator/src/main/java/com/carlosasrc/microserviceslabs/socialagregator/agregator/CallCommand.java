package com.carlosasrc.microserviceslabs.socialagregator.agregator;

import com.google.gson.JsonObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CallCommand extends HystrixCommand<ResponseEntity<String>> {

    private String url;
    private RestTemplate rest;

    public CallCommand(String url) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("githubMicroservice"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)));
        this.url = url;
        this.rest = new RestTemplate();
    }

    @Override
    protected ResponseEntity<String> run() throws Exception {
        return this.rest.getForEntity(url, String.class);
    }

    protected ResponseEntity<String> getFallBack() {
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("serviceError", "microservice is not available.");
        return new ResponseEntity<>(responseJson.toString(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}