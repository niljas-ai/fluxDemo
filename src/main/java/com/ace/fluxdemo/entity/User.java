package com.ace.fluxdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigInteger;

@Table("USERS") // Ensure it matches the DB table name
@Data
public class User {

    @Id
    @Column("ID")  // Ensure column names are uppercase (H2 default)
    private Long id;

    @Column("NAME")
    private String username;

    @Column("EMAIL")
    private String email;
}