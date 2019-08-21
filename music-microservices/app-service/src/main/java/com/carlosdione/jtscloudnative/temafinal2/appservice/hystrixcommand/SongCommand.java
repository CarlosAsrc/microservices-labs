package com.carlosdione.jtscloudnative.temafinal2.appservice.hystrixcommand;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.carlosdione.jtscloudnative.temafinal2.appservice.feign.SongClient;
import com.google.gson.JsonObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import feign.Feign;

public class SongCommand extends HystrixCommand<ResponseEntity<String>> {

    private SongClient songClient;
    private String id;
    private String url;
    private static final String KEY = "songCommand";
    

    public SongCommand(String id, String url) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(KEY))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withExecutionTimeoutInMilliseconds(5000)));
        this.url = url;
        this.songClient = Feign.builder().target(SongClient.class, this.url);
        this.id = id;
    }

    @Override
    protected ResponseEntity<String> run() {
        return new ResponseEntity<>(songClient.getSongDetail(id), HttpStatus.ACCEPTED);
    }

    @Override
    protected ResponseEntity<String> getFallback() {
    	JsonObject jsonFallback = new JsonObject();
    	jsonFallback.addProperty("Error", "Connection failed to Music Service!");
        return new ResponseEntity<>(jsonFallback.getAsString(), HttpStatus.ACCEPTED);
    }
    
}
