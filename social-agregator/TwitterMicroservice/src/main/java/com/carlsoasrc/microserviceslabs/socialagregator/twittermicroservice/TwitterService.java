package com.carlsoasrc.microserviceslabs.socialagregator.twittermicroservice;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TwitterService {

    @RequestMapping(value = "/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getUserTweetsAmount(@PathVariable String userName) {
        return new ResponseEntity<>(new TwitterCommand(userName).execute().toString(), HttpStatus.OK);
    }
}