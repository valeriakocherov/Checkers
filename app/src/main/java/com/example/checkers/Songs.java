package com.example.checkers;

public class Songs {

    private long ID;
    private String title;

    public Songs(long ID, String title) {
        this.ID = ID;
        this.title = title;
    }

    public long getId() {
        return this.ID;
    }

    public String getTitle() {
        return this.title;
    }

    public String toSring() {
        return (this.ID + ", " + this.title);
    }
}
