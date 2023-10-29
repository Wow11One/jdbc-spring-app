package com.jdbc.jdbcspringapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private Long id;
    private String name;
    private String nationality;
    private Integer jerseyNumber;
}
