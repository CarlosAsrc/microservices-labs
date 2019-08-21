package com.carlosdione.jtscloudnative.temafinal2.playlistservice.model;

import java.util.List;

public class Playlist {

    private int id;
    private List<Integer> songsId;

    public Playlist(int id, List<Integer> songsId) {
        this.id = id;
        this.songsId = songsId;
    }

    public List<Integer> getTitle() {
        return songsId;
    }

    public void setTitle(List<Integer> songsID) {
        this.songsId = songsID;
    }

    public void addSongId(Integer songId) {
        if (songsId != null) {
            songsId.add(songId);
        }
    }

    public int getId() {
        return id;
    }
}
