package com.carlosdione.jtscloudnative.temafinal2.songservice.db;

import com.carlosdione.jtscloudnative.temafinal2.songservice.model.Music;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class DatabaseAccesCommand extends HystrixCommand<String> {

    private static final SongDao songDao = new SongDao();
    private static final Gson gson = new Gson();
    private static final String KEY = "songServiceDatabase";

    private int songId;

    public DatabaseAccesCommand(int songId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(KEY))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withExecutionTimeoutInMilliseconds(10000)));
        this.songId = songId;
    }

    @Override
    protected String run() throws Exception {
        Music music = songDao.getMusic(songId);
        return music != null ? gson.toJson(music) : gson.toJson(new Music(0, "MUSIC_NOT_FOUND"));
    }

    protected String getFallBack() {
    	JsonObject jsonFallback = new JsonObject();
    	jsonFallback.addProperty("Error", "Connection failed to Song Database!");
        return jsonFallback.getAsString();
    }
}
