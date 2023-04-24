package com.example.HT;

import java.util.Date;

public class Lutemon {
    private String details;
    private Date date;
    private boolean starred = false;

    public Lutemon(String item, boolean starred)    {
        this.details = item;
        this.date = new Date();
        this.starred = starred;
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isStarred() {
        return starred;
    }
}
