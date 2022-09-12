package com.project.noticeboard.domain.post;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Post {

    private Long id;
    private String userId;
    private String title;
    private String content;
    private LocalDate registrationDate;

    public Post() {
    }

    public Post(String title, String content, LocalDate registrationDate) {
        this.title = title;
        this.content = content;
        this.registrationDate = registrationDate;
    }

    public Post(String userId, String title, String content, LocalDate registrationDate) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.registrationDate = registrationDate;
    }
}
