package com.carlosdione.jtscloudnative.temafinal2.songservice.discovery;

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

}
