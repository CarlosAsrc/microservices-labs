package com.carlsoasrc.microserviceslabs.socialagregator.twittermicroservice;

import com.google.gson.JsonObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.Status;
import twitter4j.Twitter;

import java.util.List;

public class TwitterCommand extends HystrixCommand<JsonObject> {

    @Autowired
    private Twitter twitter;
    private String userName;

    public TwitterCommand(String userName) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("twitterMicroservice"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10000)));
        this.userName = userName;
    }

    @Override
    protected JsonObject run() throws Exception {
        JsonObject twitterResponse = new JsonObject();
        List<Status> twitterApiResponse = twitter.getUserTimeline(userName);
        int userTweetsCount = twitterApiResponse.get(0).getUser().getStatusesCount();
        twitterResponse.addProperty("userTweetsCount", userTweetsCount);
        twitterResponse.addProperty("error", "No user found!");
        return twitterResponse;
    }

    protected JsonObject getFallBack() {
        JsonObject response = new JsonObject();
        response.addProperty("error", "No user found!");
        return response;
    }
}