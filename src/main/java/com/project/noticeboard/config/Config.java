package com.project.noticeboard.config;

import com.project.noticeboard.Repository.comment.CommentRepository;
import com.project.noticeboard.Repository.comment.JpaCommentRepository;
import com.project.noticeboard.Repository.member.JpaMemberRepository;
import com.project.noticeboard.Repository.member.MemberRepository;
import com.project.noticeboard.Repository.post.JpaPostRepository;
import com.project.noticeboard.Repository.post.PostRepository;
import com.project.noticeboard.service.comment.CommentService;
import com.project.noticeboard.service.member.MemberServiceImpl;
import com.project.noticeboard.service.member.MemberService;
import com.project.noticeboard.service.post.PostServiceImpl;
import com.project.noticeboard.service.post.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class Config {

    private final EntityManager em;

    public Config(EntityManager em) {
        this.em = em;
    }

    @Bean
    public PostService postService() {
        return new PostServiceImpl(postRepository());
    }

    @Bean
    public PostRepository postRepository() {
        return new JpaPostRepository(em);
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public CommentRepository commentRepository() {
        return new JpaCommentRepository(em);
    }

    @Bean
    public CommentService commentService() {
        return new CommentService(commentRepository());
    }
}
