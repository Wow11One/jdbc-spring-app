package com.jdbc.jdbcspringapp.jdbc_implementation.dao;

import com.jdbc.jdbcspringapp.jdbc_implementation.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class PlayerDaoImpl implements FootballDao<Player> {

    private final DataSource dataSource;

    @Override
    public void create(Player player) {
        try (Connection connection = dataSource.getConnection()) {
            if (findOne(player.id()) != null) {
                throw new SQLException("player with such id already exists");
            }
            String query = "INSERT INTO player (player_name, nationality, jersey_number) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, player.name());
            preparedStatement.setString(2, player.nationality());
            preparedStatement.setInt(3, player.jerseyNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Player player) {
        if (findOne(player.id()) == null) {
            throw new NoSuchElementException("player with such id does not exist");
        }
        try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE player SET player_name = ?, nationality = ?, jersey_number = ? WHERE player_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, player.name());
            preparedStatement.setString(2, player.nationality());
            preparedStatement.setInt(3, player.jerseyNumber());
            preparedStatement.setLong(4, player.id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM player WHERE player_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Player findOne(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM player WHERE player_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next() ? getPlayerFromResultSet(rs) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Player> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM player";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            List<Player> res = new ArrayList<>();
            while (rs.next()) {
                res.add(getPlayerFromResultSet(rs));
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Player getPlayerFromResultSet(ResultSet rs) throws SQLException {
        Player player = new Player(rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4));
        return player;
    }
}
