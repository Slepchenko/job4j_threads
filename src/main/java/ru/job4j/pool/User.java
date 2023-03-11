package ru.job4j.pool;

public class User {

    private String username;

    private String email;

    public User(String userName, String email) {
        this.username = userName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
