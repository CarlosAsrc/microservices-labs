package com.carlosdione.jtscloudnative.temafinal2.appservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class AppRestController {

    @Autowired
    private AppService appService;

    @RequestMapping(value = "/playlist/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getPlayList(@PathVariable String id) {
    	return new ResponseEntity<>(appService.getSongDetailsByPlaylist(id), HttpStatus.OK);
    }

}
