package com.project.noticeboard.web.auth;

import com.project.noticeboard.domain.member.Member;
import com.project.noticeboard.domain.member.MemberLoginDto;
import com.project.noticeboard.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("memberLoginDto") MemberLoginDto form) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute MemberLoginDto memberLoginDto,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        Member loginMember = authService.login(memberLoginDto.getEmail(), memberLoginDto.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }

        return "redirect:/boards";
    }
}
