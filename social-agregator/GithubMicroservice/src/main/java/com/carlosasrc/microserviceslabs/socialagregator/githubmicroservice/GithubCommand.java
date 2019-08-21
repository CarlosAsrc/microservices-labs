package com.carlosasrc.microserviceslabs.socialagregator.githubmicroservice;

import com.google.gson.JsonObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.eclipse.egit.github.core.service.RepositoryService;

public class GithubCommand extends HystrixCommand<JsonObject> {

    private String userName;
    private RepositoryService repositoryService;

    public GithubCommand(String userName) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("githubMicroservice"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10000)));
        this.userName = userName;
        repositoryService = new RepositoryService();
    }

    @Override
    protected JsonObject run() throws Exception {
        JsonObject response = new JsonObject();
        int amount = repositoryService.getRepositories(userName).size();
        response.addProperty("userRepositoriesCount", amount);
        return response;
    }

    protected JsonObject getFallBack() {
        JsonObject response = new JsonObject();
        response.addProperty("error", "No user found!");
        return response;
    }
}