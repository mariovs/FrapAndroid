package com.mario22gmail.vasile.frap.NewsFeed;

/**
 * Created by mario.vasile on 12/20/2016.
 */

public class News {
    private String Name;
    private String Description;

    public News(String name, String description)
    {
        this.Name = name;
        this.Description = description;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }
}
