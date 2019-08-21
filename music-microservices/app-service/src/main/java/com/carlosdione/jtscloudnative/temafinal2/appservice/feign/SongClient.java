package com.carlosdione.jtscloudnative.temafinal2.appservice.feign;

import feign.Param;
import feign.RequestLine;

public interface SongClient {
    @RequestLine("GET /song/{id}")
    String getSongDetail(@Param("id") String id);
}
