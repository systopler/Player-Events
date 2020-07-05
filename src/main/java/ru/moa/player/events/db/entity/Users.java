package ru.moa.player.events.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
public class Users extends DeletableEntity<Long> {
    private String login;
    private String password;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Long getId(){
        return id;
    };


    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    @JsonIgnore
    @Column(name = "user_password")
    public String getPassword() {
        return password;
    }
}
