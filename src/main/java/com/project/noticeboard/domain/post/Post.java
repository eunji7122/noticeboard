package com.project.noticeboard.domain.post;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    private String title;
    private String content;

    public Post() {
    }

    public Post(String title, String content, LocalDate registrationDate) {
        this.title = title;
        this.content = content;
        this.registrationDate = registrationDate;
    }

    public Post(Long id, String email, String password, LocalDate registrationDate, String title, String content) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.title = title;
        this.content = content;
    }
}
