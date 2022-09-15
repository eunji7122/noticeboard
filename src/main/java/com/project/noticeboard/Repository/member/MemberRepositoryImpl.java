package com.project.noticeboard.Repository.member;

import com.project.noticeboard.domain.member.Member;

import java.util.Optional;

public interface MemberRepositoryImpl {

    Member save(Member member);

    Member findById(Long id);

    Optional<Member> findByEmail(String email);

}
