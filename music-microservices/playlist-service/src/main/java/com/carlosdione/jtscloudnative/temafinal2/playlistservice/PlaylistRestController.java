package com.carlosdione.jtscloudnative.temafinal2.playlistservice;

import com.carlosdione.jtscloudnative.temafinal2.playlistservice.db.DatabaseAccesCommand;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class PlaylistRestController {

	@RequestMapping(value = "/playlist/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getPlaylist(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(new DatabaseAccesCommand(id).execute(), HttpStatus.OK);
    }

}
