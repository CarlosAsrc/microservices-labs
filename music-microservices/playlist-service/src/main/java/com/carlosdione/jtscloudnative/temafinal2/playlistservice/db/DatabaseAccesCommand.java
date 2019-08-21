package com.carlosdione.jtscloudnative.temafinal2.playlistservice.db;

import java.util.ArrayList;

import com.carlosdione.jtscloudnative.temafinal2.playlistservice.model.Playlist;
import com.google.gson.Gson;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class DatabaseAccesCommand extends HystrixCommand<String> {

    private static final PlaylistDao songDao = new PlaylistDao();
    private static final Gson gson = new Gson();
    private static final String KEY = "playlistServiceDatabase";

    private int playlistId;

    public DatabaseAccesCommand(int playlistId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(KEY))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withExecutionTimeoutInMilliseconds(10000)));
        this.playlistId = playlistId;
    }

    @Override
    protected String run() throws Exception {
        Playlist playlist = songDao.getPlaylist(playlistId);
        return playlist != null ? gson.toJson(playlist) : gson.toJson(new Playlist(0, new ArrayList<>()));
    }

    protected String getFallBack() {
        return "{\"Error\":\"Fallback return\"}";
    }
}
