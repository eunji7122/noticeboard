package com.project.noticeboard.domain.post;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {

    private static final Map<Long, Post> storage = new HashMap<>();
    private static long sequence = 0L;

    public Post save(Post post) {
        post.setId(++sequence);
        post.setRegistrationDate(LocalDate.now());
        storage.put(post.getId(), post);
        return post;
    }

    public Post findById(Long id) {
        return storage.get(id);
    }

    public List<Post> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(Long postId, Post updateParam) {
        Post findPost = findById(postId);
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
    }

    public void delete(Long postId) {
        storage.remove(postId);
    }

    public void clearStorage() {
        storage.clear();
    }
}
