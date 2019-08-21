package com.carlosdione.jtscloudnative.temafinal2.songservice;

import com.carlosdione.jtscloudnative.temafinal2.songservice.db.DatabaseAccesCommand;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SongRestController {

    @RequestMapping(value = "/song/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getSongDetail(@PathVariable("id") Integer id) {
    	return new ResponseEntity<>(new DatabaseAccesCommand(id).execute(), HttpStatus.OK);
    }

}
