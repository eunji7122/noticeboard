package com.project.noticeboard.service.auth;

import com.project.noticeboard.Repository.member.MemberRepository;
import com.project.noticeboard.domain.auth.GoogleUserInfo;
import com.project.noticeboard.domain.auth.NaverUserInfo;
import com.project.noticeboard.domain.auth.OAuth2UserInfo;
import com.project.noticeboard.domain.auth.PrincipalDetails;
import com.project.noticeboard.domain.member.Member;
import com.project.noticeboard.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();

        if(provider.equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(provider.equals("naver")){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }

        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);  // 사용자가 입력한 적은 없지만 만들어준다

        String email = oAuth2UserInfo.getEmail();
        Role role = Role.USER;

        Optional<Member> findMember = memberRepository.findByEmail(email);

        //DB에 없는 사용자라면 회원가입처리
        if(findMember.isEmpty()){
            findMember = Optional.ofNullable(Member.oauth2Register()
                    .email(email)
                    .password(password).username(username).phoneNumber(null).role(role)
                    .provider(provider).providerId(providerId)
                    .build());
            memberRepository.save(findMember.get());
        }

        return new PrincipalDetails(findMember.get(), oAuth2UserInfo);
    }
}
