package ru.moa.player.events.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login")
    private String login;

    @JsonIgnore
    @Column(name = "user_password")
    private String password;

}
