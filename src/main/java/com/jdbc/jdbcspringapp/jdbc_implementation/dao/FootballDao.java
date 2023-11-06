package com.jdbc.jdbcspringapp.jdbc_implementation.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

public interface FootballDao<T> {
    void create(T t);
    void update(T t);
    void delete(Long id);
    T findOne(Long id);
    List<T> findAll();
}
