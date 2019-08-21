package com.carlsoasrc.microserviceslabs.socialagregator.twittermicroservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@PropertySource("classpath:twitter.properties")
public class TwitterConfig {

    @Value("${consumerKey}")
    private String CONSUMER_KEY;
    @Value("${consumerSecret}")
    private String CONroSUMER_SECRET;
    @Value("${accessToken}")
    private String ACCESS_TOKEN;
    @Value("${accessTokenSecret}")
    private String ACCESS_TOKEN_SECRET;

    @Bean
    public Twitter twitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(this.CONSUMER_KEY)
                .setOAuthConsumerSecret(this.CONSUMER_SECRET)
                .setOAuthAccessToken(this.ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(this.ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return  tf.getInstance();
    }
}