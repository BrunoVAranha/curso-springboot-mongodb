package com.bruno.cursomongo.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentDto implements Serializable {

    private String text;
    private Date date;
    private AuthorDTO author;

    public CommentDto(){

    }

    public CommentDto(String text, Date date, AuthorDTO author) {
        this.text = text;
        this.date = date;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setName(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
