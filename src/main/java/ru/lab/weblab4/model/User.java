package ru.lab.weblab4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity{

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;
}
