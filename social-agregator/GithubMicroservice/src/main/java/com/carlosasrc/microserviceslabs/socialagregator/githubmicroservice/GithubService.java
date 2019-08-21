package com.carlosasrc.microserviceslabs.socialagregator.githubmicroservice;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class GithubService {

    @RequestMapping(value = "/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getTotalUserRepositories(@PathVariable String userName) {
        return  new ResponseEntity<>(new GithubCommand(userName).execute().toString(), HttpStatus.OK);
    }
}
