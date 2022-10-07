package com.project.noticeboard.Repository.comment;

import com.project.noticeboard.domain.comment.Comment;
import com.project.noticeboard.domain.comment.CommentUpdateDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.project.noticeboard.domain.comment.QComment.comment;

@Slf4j
@Repository
@Transactional
public class JpaCommentRepository implements CommentRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaCommentRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Comment save(Comment comment) {
        comment.setRegistrationDate(LocalDate.now());
        em.persist(comment);
        return comment;
    }

    @Override
    public void update(Long commentId, CommentUpdateDto commentUpdateDto) {
        Comment findComment = em.find(Comment.class, commentId);
        findComment.setText(commentUpdateDto.getText());
    }

    @Override
    public List<Comment> findAll(Long postId) {
        return query.select(comment)
                .from(comment)
                .where(comment.post.id.like(String.valueOf(postId)))
                .fetch();
    }
}
