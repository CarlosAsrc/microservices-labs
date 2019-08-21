package com.carlosdione.jtscloudnative.temafinal2.songservice.db;

import com.carlosdione.jtscloudnative.temafinal2.songservice.model.Music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongDao {

    private static final String SELECT_BY_ID = "SELECT * FROM song WHERE id = ?";

    public Music getMusic(int songId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, songId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Music(resultSet.getInt("id"), resultSet.getString("title"));
            }
            return null;
        } catch (SQLException exception) {
            throw exception;
        }
    }

}
