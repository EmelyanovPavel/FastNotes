package com.example.fastnotes;

public class Note {
    private long id; // homework 14
    private String title;
    private String content;

    public Note(long id, String title, String content) {
        this.id = id;// homework 14
        this.title = title;
        this.content = content;
    }
    // homework 14
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}