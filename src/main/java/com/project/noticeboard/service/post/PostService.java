package com.project.noticeboard.service.post;

import com.project.noticeboard.Repository.post.PostRepositoryImpl;
import com.project.noticeboard.domain.post.PostSearchCond;
import com.project.noticeboard.domain.post.PostUpdateDto;
import com.project.noticeboard.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService implements PostServiceImpl{

    private final PostRepositoryImpl postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void update(Long postId, PostUpdateDto updateParam) {
        postRepository.update(postId, updateParam);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findPosts(PostSearchCond cond) {
        return postRepository.findAll(cond);
    }

    @Override
    public void delete(Long id) {
        postRepository.delete(id);
    }
}
