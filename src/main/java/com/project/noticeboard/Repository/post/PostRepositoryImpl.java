package com.project.noticeboard.Repository.post;

import com.project.noticeboard.domain.post.Post;
import com.project.noticeboard.domain.post.PostSearchCond;
import com.project.noticeboard.domain.post.PostUpdateDto;

import java.util.List;
import java.util.Optional;

public interface PostRepositoryImpl {

    Post save(Post post);

    void update(Long postId, PostUpdateDto updateParam);

    Optional<Post> findById(Long id);

    List<Post> findAll(PostSearchCond cond);

    void delete(Long id);
}
