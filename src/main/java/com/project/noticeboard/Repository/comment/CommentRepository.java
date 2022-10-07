package com.project.noticeboard.Repository.comment;

import com.project.noticeboard.domain.comment.Comment;
import com.project.noticeboard.domain.comment.CommentUpdateDto;
import com.project.noticeboard.domain.post.Post;

import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);

    void update(Long commentId, CommentUpdateDto commentUpdateDto);

    List<Comment> findAll(Long postId);
}
