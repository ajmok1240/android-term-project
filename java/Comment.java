package com.example.project;

public class Comment {
    private String name;
    private String content;

    public Comment(String name, String content){
        this.name = name;
        this.content = content;
    }

    public String getName()
    {
        return this.name;
    }

    public String getContent()
    {
        return this.content;
    }
}
