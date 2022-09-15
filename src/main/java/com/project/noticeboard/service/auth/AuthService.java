package com.project.noticeboard.service.auth;

import com.project.noticeboard.Repository.member.MemberRepositoryImpl;
import com.project.noticeboard.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepositoryImpl memberRepository;

    public Member login(String email, String password) {
        return memberRepository.findByEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
