package com.carlosdione.jtscloudnative.temafinal2.songservice.model;

public class Music {

    private int id;
    private String title;

    public Music(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }
}
