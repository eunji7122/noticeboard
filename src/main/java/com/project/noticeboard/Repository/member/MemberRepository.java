package com.project.noticeboard.Repository.member;

import com.project.noticeboard.domain.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@Transactional
public class MemberRepository implements MemberRepositoryImpl{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public MemberRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return null;
    }
}
