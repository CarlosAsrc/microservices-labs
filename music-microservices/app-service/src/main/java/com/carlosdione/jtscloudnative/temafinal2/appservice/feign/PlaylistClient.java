package com.carlosdione.jtscloudnative.temafinal2.appservice.feign;

import feign.Param;
import feign.RequestLine;

public interface PlaylistClient {
    @RequestLine("GET /playlist/{id}")
    String getPlaylist(@Param("id")String id);
}
