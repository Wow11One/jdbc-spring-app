package com.jdbc.jdbcspringapp.hibernate_implementation.dao;

import com.jdbc.jdbcspringapp.hibernate_implementation.model.Club;
import com.jdbc.jdbcspringapp.hibernate_implementation.util.JpaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ClubDao {

    private final JpaUtil jpaUtil;

    public void save(Club club) {
        jpaUtil.performWithinPersistenceContext(session -> session.persist(club));
    }

    public void update(Club club) {
        jpaUtil.performWithinPersistenceContext(session -> session.merge(club));
    }

    public Club findById(Long id) {
        return jpaUtil.performReturningWithinPersistenceContext(session ->
                session.createQuery("select c from Club c  where c.id = :id ", Club.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    public List<Club> findAll() {
        return jpaUtil.performReturningWithinPersistenceContext(session ->
                session.createQuery("select c from Club c", Club.class)
                        .getResultList());
    }

    public void delete(Club club) {
        jpaUtil.performWithinPersistenceContext(session -> {
                    Club managedClub = session.merge(club);
                    session.remove(managedClub);
                }
        );
    }

}
