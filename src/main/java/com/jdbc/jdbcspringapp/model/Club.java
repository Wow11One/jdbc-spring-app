package com.jdbc.jdbcspringapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Club {
    private Long id;
    private String name;
    private String country;
    private String stadium;
}
