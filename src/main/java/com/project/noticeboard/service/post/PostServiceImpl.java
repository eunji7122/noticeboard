package com.project.noticeboard.service.post;

import com.project.noticeboard.domain.post.PostSearchCond;
import com.project.noticeboard.domain.post.PostUpdateDto;
import com.project.noticeboard.domain.post.Post;

import java.util.List;
import java.util.Optional;

public interface PostServiceImpl {

    Post save(Post post);

    void update(Long postId, PostUpdateDto updateParam);

    Optional<Post> findById(Long id);

    List<Post> findPosts(PostSearchCond cond);

    void delete(Long id);
}
