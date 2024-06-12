package com.vianny.dverivariant.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table()
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String login;
    @Column
    private String password;

    public Accounts() {
    }

    public Accounts(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return Objects.equals(id, accounts.id) && Objects.equals(login, accounts.login) && Objects.equals(password, accounts.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }
}
