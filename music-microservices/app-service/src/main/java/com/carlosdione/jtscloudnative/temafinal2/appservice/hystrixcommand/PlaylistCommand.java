package com.carlosdione.jtscloudnative.temafinal2.appservice.hystrixcommand;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.carlosdione.jtscloudnative.temafinal2.appservice.feign.PlaylistClient;
import com.google.gson.JsonObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import feign.Feign;

public class PlaylistCommand extends HystrixCommand<ResponseEntity<String>> {

    private PlaylistClient playlistClient;
    private String id;
    private String url;
    private static final String KEY = "playlistCommand";

    public PlaylistCommand(String id, String url) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(KEY))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)));
        this.url = url;
        this.playlistClient = Feign.builder().target(PlaylistClient.class, this.url);
        this.id = id;
    }

    @Override
    protected ResponseEntity<String> run() throws Exception {
        return new ResponseEntity<>(playlistClient.getPlaylist(id), HttpStatus.ACCEPTED);
    }

    @Override
    protected ResponseEntity<String> getFallback() {
    	JsonObject jsonFallback = new JsonObject();
    	jsonFallback.addProperty("Error", "Connection failed to Playlist Service!");
        return new ResponseEntity<>(jsonFallback.getAsString(), HttpStatus.ACCEPTED);
    }
}