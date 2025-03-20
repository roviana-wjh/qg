package org.example;

public class user {
    private int id;
    private String username;
    private String password;
    private int role;

    @Override
    public String toString() {
        return "org.example.user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public int getRole(int role) {
        return this.role;
    }
    public int getrole(){return role;}

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword(String password) {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId(int id) {
        return this.id;
    }
    public int getId(){return this.id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername(String username) {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
// Getters and Setters

}