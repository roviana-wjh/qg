package org.example;

public class course {
    private int id;

    private String name;
    private int credit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String toString() {
        return "org.example.course{" +
                "credit=" + credit +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
// Getters and Setters
}