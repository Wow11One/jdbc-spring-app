package com.jdbc.jdbcspringapp.hibernate_implementation.dao;

import com.jdbc.jdbcspringapp.hibernate_implementation.model.Club;
import com.jdbc.jdbcspringapp.hibernate_implementation.model.Player;
import com.jdbc.jdbcspringapp.hibernate_implementation.util.JpaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PlayerDao {

    private final JpaUtil jpaUtil;

    public void save(Player player) {
        jpaUtil.performWithinPersistenceContext(session -> {
            Club mergedClub = session.merge(player.getClub());
            player.setClub(mergedClub);
            session.persist(player);
        });
    }

    public void update(Player player) {
        jpaUtil.performWithinPersistenceContext(session -> session.merge(player));
    }

    public Player findById(Long id) {
        return jpaUtil.performReturningWithinPersistenceContext(session -> {
                    Player player = session.getReference(Player.class, id);
                    Club club = session.getReference(Club.class, player.getClub().getId());
                    player.setClub(club);
                   // System.out.println(club);
                    return player;
                }
        );
    }

    public List<Player> findAll() {
        return jpaUtil.performReturningWithinPersistenceContext(session ->
                session.createQuery("select c from Player c", Player.class)
                        .getResultList());
    }

    public void delete(Player player) {
        jpaUtil.performWithinPersistenceContext(session -> {
                    Player managedPlayer = session.merge(player);
                    session.remove(managedPlayer);
                }
        );
    }

}