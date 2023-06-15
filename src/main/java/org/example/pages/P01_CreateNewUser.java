package org.example.pages;

public class P01_CreateNewUser {
    private String name;
    private String job;
    public String getName() {
        return this.name;
    }

    public P01_CreateNewUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getJob() {
        return this.job;
    }

    public P01_CreateNewUser setJob(String job) {
        this.job = job;
        return this;
    }

}
