package com.project.noticeboard.Repository.member;

import com.project.noticeboard.domain.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.project.noticeboard.domain.member.QMember.member;

@Slf4j
@Repository
@Transactional
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        List<Member> findMember = query.select(member)
                .from(member)
                .where(member.email.like(email))
                .fetch();
        if (findMember.size() != 0) {
            return Optional.ofNullable(findMember.get(0));
        }
        return Optional.empty();
    }
}
