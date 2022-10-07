package com.project.noticeboard.service.comment;

import com.project.noticeboard.Repository.comment.CommentRepository;
import com.project.noticeboard.domain.comment.Comment;
import com.project.noticeboard.domain.comment.CommentUpdateDto;
import com.project.noticeboard.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void update(Long commentId, CommentUpdateDto commentUpdateDto) {
        commentRepository.update(commentId, commentUpdateDto);
    }

    public List<Comment> findComments(Long postId) {
        return commentRepository.findAll(postId);
    }
}
