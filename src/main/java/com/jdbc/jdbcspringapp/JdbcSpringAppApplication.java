package com.jdbc.jdbcspringapp;

import com.jdbc.jdbcspringapp.hibernate_implementation.dao.ClubDao;
import com.jdbc.jdbcspringapp.hibernate_implementation.dao.PlayerDao;
import com.jdbc.jdbcspringapp.hibernate_implementation.model.Club;
import com.jdbc.jdbcspringapp.hibernate_implementation.model.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JdbcSpringAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JdbcSpringAppApplication.class, args);
        ClubDao clubDao = context.getBean(ClubDao.class);
        System.out.println("club:" + clubDao.findAll());
        Club cityFC = new Club("Manchester city", "England", "Etihad");
        clubDao.save(cityFC);
        Club club = clubDao.findById(1L);
        System.out.println("club:" + club);
        //club.setName("Shakhtar");
        //clubDao.update(club);
        System.out.println(club);


        PlayerDao playerDao = context.getBean(PlayerDao.class);

        playerDao.save(new Player("Mykhailo Mudryk", "Ukraine", 10,cityFC));

       // System.out.println(playerDao.findById(1L));
        clubDao.delete(cityFC);


    }

}
