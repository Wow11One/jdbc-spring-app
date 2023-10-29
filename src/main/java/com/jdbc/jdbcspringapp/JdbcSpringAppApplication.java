package com.jdbc.jdbcspringapp;

import com.jdbc.jdbcspringapp.dao.ClubDaoImpl;
import com.jdbc.jdbcspringapp.dao.FootballDao;
import com.jdbc.jdbcspringapp.dao.PlayerDaoImpl;
import com.jdbc.jdbcspringapp.model.Club;
import com.jdbc.jdbcspringapp.model.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JdbcSpringAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JdbcSpringAppApplication.class, args);
        FootballDao<Club> clubFootballDao = context.getBean(ClubDaoImpl.class);
        Club clubToBeUpdated = new Club(2L, "Liverpool FC", "England", "Anfield");
        Club clubToBeCreated = new Club(6L, "Polissya FC", "Ukraine", "Zhytomyr Stadium");
        System.out.println(clubFootballDao.findOne(2L));
        clubFootballDao.update(clubToBeUpdated);
        System.out.println(clubFootballDao.findOne(2L));
        clubFootballDao.delete(7L);
        clubFootballDao.create(clubToBeCreated);
        System.out.println(clubFootballDao.findAll());

        FootballDao<Player> playerFootballDao = context.getBean(PlayerDaoImpl.class);
        Player playerToBeUpdated = new Player(2L, "Harry Kane", "England", 10);
        Player playerToBeCreated = new Player(8L, "Oleksandr Zinchenko", "Ukraine", 11);
        System.out.println(clubFootballDao.findOne(2L));
        playerFootballDao.update(playerToBeUpdated);
        System.out.println(playerFootballDao.findOne(2L));
        playerFootballDao.delete(8L);
        System.out.println(playerFootballDao.findOne(8L));
        playerFootballDao.create(playerToBeCreated);
        System.out.println(playerFootballDao.findAll());

    }

}
