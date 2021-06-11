package com.intech.test.domain;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Message implements Comparable<Message> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;

    private Date time;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Message() {
    }

    public Message(String text, User user) {
        this.author = user;
        this.text = text;
        this.time = new Date();
    }

    public Date getTime() {
        return time;
    }

    public String getFormatDateAndTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
        if (time != null)
            return (dateFormat.format(time));
        else
            return "Null";
    }


    public void setTime(Date time) {
        this.time = time;
    }

    public String getAuthorName() {
        return author.getUsername();
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int compareTo(@NotNull Message o) {
        if (time==null || o.getTime()==null)
            return 0;
        return o.getTime().compareTo(time);
    }
}
