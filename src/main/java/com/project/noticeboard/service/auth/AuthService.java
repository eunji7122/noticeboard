package com.project.noticeboard.service.auth;

import com.project.noticeboard.Repository.member.MemberRepositoryImpl;
import com.project.noticeboard.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepositoryImpl memberRepository;

    private final AuthenticationManager authenticationManager;

    public Member login(String email, String password) {
        log.info(authenticationManager.getClass().getName());
//        Authentication authentication = authenticationManager
//                .authenticate((new UsernamePasswordAuthenticationToken(email, password)));
//        log.info("1111111111");
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        return memberRepository.findByEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
