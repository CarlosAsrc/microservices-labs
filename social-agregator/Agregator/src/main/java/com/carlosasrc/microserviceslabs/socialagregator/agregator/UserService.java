package com.carlosasrc.microserviceslabs.socialagregator.agregator;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class UserService {

    @Value("${twitterurl}")
    private String twitterUrl;
    @Value("${githuburl}")
    private String githubUrl;


    @RequestMapping(value = "/github/{githubUser}/twitter/{twitterUser}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getUserData(@PathVariable String githubUser, @PathVariable String twitterUser) {
        ResponseEntity<String> responseGithub = new CallCommand(githubUrl+githubUser).execute();
        ResponseEntity<String> responseTwitter = new CallCommand(twitterUrl+twitterUser).execute();

        JsonParser jsonParser = new JsonParser();
        JsonElement githubElement = jsonParser.parse(responseGithub.getBody());
        JsonElement twitterElement = jsonParser.parse(responseTwitter.getBody());

        JsonObject responseJson = new JsonObject();
        responseJson.add("github", githubElement);
        responseJson.add("twitter", twitterElement);
        return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/twitter/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getTweetsCount(@PathVariable String userName) {
        return new CallCommand(twitterUrl+userName).execute();
    }

    @RequestMapping(value = "/github/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getRepositoriesCount(@PathVariable String userName) {
        return new CallCommand(githubUrl+userName).execute();
    }
}