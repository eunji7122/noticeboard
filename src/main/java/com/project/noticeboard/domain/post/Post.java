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
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    private String username;
    private String title;
    private String content;

    public Post() {
    }

    public Post(Long id, LocalDate registrationDate, String title, String content) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.title = title;
        this.content = content;
    }
}
