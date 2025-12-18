package com.example.fastnotes.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private long id; // homework 14
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "date")
    private long date; //homework 18


    public Note(long id, String title, String content, long date) {
        this.id = id;// homework 14
        this.title = title;
        this.content = content;
        this.date = date;
    }
    // homework 14
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}