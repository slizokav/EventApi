package com.HaEventApi.EventApi.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "people")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "username")
    @NotEmpty(message = "Имя пользователя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Недопустимое значение имени пользователя")
    private String username;


    @Column(name = "password")
    @NotEmpty(message = "Пароль не должен быть пустым")
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
