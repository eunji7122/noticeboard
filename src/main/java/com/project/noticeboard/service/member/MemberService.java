package com.project.noticeboard.service.member;

import com.project.noticeboard.Repository.member.MemberRepositoryImpl;
import com.project.noticeboard.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceImpl{

    private final MemberRepositoryImpl memberRepository;

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
