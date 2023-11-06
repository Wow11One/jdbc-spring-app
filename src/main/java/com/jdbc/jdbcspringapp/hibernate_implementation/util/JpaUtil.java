package com.jdbc.jdbcspringapp.hibernate_implementation.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class JpaUtil {
    private SessionFactory sessionFactory;

    @PostConstruct
    private void buildSessionFactory() {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void performWithinPersistenceContext(Consumer<EntityManager> operation) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            operation.accept(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error performing JPA operation. Transaction is rolled back", e);
        } finally {
            session.close();
        }
    }

    public <T> T performReturningWithinPersistenceContext(Function<EntityManager, T> entityManagerFunction) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        try {
            T result = entityManagerFunction.apply(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error performing JPA operation. Transaction is rolled back", e);
        } finally {
            session.close();
        }
    }

    @PreDestroy
    public void shutdown() {
        sessionFactory.close();
    }
}
