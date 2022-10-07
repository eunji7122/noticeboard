package com.project.noticeboard.domain.post;

import com.project.noticeboard.domain.comment.Comment;
import com.project.noticeboard.domain.member.Member;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;
    private String title;
    private String content;

    @OneToMany
    @JoinColumn(name = "comment")
    private List<Comment> comments;

    public Post() {
    }

    public Post(Long id, LocalDate registrationDate, String title, String content) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.title = title;
        this.content = content;
    }
}
