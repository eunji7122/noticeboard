package com.project.noticeboard.config;

import com.project.noticeboard.Repository.post.PostRepository;
import com.project.noticeboard.Repository.post.PostRepositoryImpl;
import com.project.noticeboard.service.post.PostService;
import com.project.noticeboard.service.post.PostServiceImpl;
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
    public PostServiceImpl postService() {
        return new PostService(postRepository());
    }

    @Bean
    public PostRepositoryImpl postRepository() {
        return new PostRepository(em);
    }
}
