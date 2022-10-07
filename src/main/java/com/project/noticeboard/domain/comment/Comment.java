package com.project.noticeboard.domain.comment;

import com.project.noticeboard.domain.member.Member;
import com.project.noticeboard.domain.post.Post;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

    private String text;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public Comment() {

    }

    public Comment(Member member, Post post, String text, LocalDate registrationDate) {
        this.member = member;
        this.post = post;
        this.text = text;
        this.registrationDate = registrationDate;
    }

}
