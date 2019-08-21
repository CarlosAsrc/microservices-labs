package com.carlosdione.jtscloudnative.temafinal2.appservice.discovery;

import org.springframework.web.bind.annotation.ResponseBody;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface EurekaHttpMethods {

    @RequestLine("POST /{serviceName}")
    @Headers("Content-Type: application/json")
    @Body("{jsonString}")
    public void registry(@Param("jsonString")String jsonString, @Param("serviceName")String serviceName);

    @RequestLine("PUT /{serviceName}/{hostName}/status?value=UP")
    public void updateToUP(@Param("serviceName")String serviceName, @Param("hostName")String hostName);

    @RequestLine("PUT /{serviceName}/{hostName}")
    public void heartBeat(@Param("serviceName")String serviceName, @Param("hostName")String hostName);

    @RequestLine("DELETE /{serviceName}/{hostName}")
    public void delete(@Param("serviceName")String serviceName, @Param("hostName")String hostName);

    
    @RequestLine("GET /{serviceName}")
    @Headers("Accept: application/json")
    @ResponseBody
    public String getInstances(@Param("serviceName")String serviceName);
    
}
