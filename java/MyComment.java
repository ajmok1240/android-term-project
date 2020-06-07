package com.example.project;

public class MyComment {
    private  String mountain;
    private String name;
    private String content;

    public MyComment(String mountain, String name, String content){
        this.mountain = mountain;
        this.name = name;
        this.content = content;
    }
    public String getMountain() { return this.mountain; }

    public String getName()
    {
        return this.name;
    }

    public String getContent()
    {
        return this.content;
    }
}
