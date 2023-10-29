package com.jdbc.jdbcspringapp.dao;

import com.jdbc.jdbcspringapp.model.Club;
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
public class ClubDaoImpl implements FootballDao<Club> {
    private final DataSource dataSource;

    @Override
    public void create(Club club) {
        try (Connection connection = dataSource.getConnection()) {
            if (findOne(club.getId()) != null) {
                throw new SQLException("club with such id already exists");
            }
            String query = "INSERT INTO club (club_name, country, stadium) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, club.getName());
            preparedStatement.setString(2, club.getCountry());
            preparedStatement.setString(3, club.getStadium());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Club club) {
        if (findOne(club.getId()) == null) {
            throw new NoSuchElementException("club with such id does not exist");
        }
        try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE club SET club_name = ?, country = ?, stadium = ? WHERE club_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, club.getName());
            preparedStatement.setString(2, club.getCountry());
            preparedStatement.setString(3, club.getStadium());
            preparedStatement.setLong(4, club.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM club WHERE club_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Club findOne(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM club WHERE club_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next() ? getClubFromResultSet(rs) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Club getClubFromResultSet(ResultSet rs) throws SQLException {
        Club club = new Club(rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4));
        return club;
    }

    @Override
    public List<Club> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM club";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            List<Club> res = new ArrayList<>();
            while (rs.next()) {
                res.add(getClubFromResultSet(rs));
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
