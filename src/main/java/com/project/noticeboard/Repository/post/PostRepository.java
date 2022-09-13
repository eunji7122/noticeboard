package com.project.noticeboard.Repository.post;

import com.project.noticeboard.domain.post.Post;
import com.project.noticeboard.domain.post.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.*;

import static com.project.noticeboard.domain.post.QPost.post;

@Slf4j
@Repository
@Transactional
public class PostRepository implements PostRepositoryImpl{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public PostRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Post save(Post post) {
        post.setRegistrationDate(LocalDate.now());
        em.persist(post);
        return post;
    }

    @Override
    public void update(Long postId, PostUpdateDto updateParam) {
        Post findPost = em.find(Post.class, postId);
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post post = em.find(Post.class, id);
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findAll(PostSearchCond cond) {
        return query.select(post)
                .from(post)
                .fetch();
    }

    @Override
    public void delete(Long id) {
        em.remove(findById(id).get());
    }
}
