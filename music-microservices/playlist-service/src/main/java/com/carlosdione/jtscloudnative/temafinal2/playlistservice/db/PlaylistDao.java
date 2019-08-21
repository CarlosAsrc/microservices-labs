package com.carlosdione.jtscloudnative.temafinal2.playlistservice.db;

import com.carlosdione.jtscloudnative.temafinal2.playlistservice.model.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDao {

    private static final String SELECT_BY_ID = "SELECT * FROM playlist WHERE id_playlist = ?";

    public Playlist getPlaylist(int playlistId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, playlistId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                List<Integer> songs = new ArrayList<>();
                while (resultSet.next()) {
                    songs.add(resultSet.getInt("id_song"));
                }
                return new Playlist(playlistId, songs);
            }
            return null;
        } catch (SQLException exception) {
            throw exception;
        }
    }

}
