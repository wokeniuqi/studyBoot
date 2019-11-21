package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer age;
}

