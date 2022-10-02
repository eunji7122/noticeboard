package com.project.noticeboard.web.auth;

import com.project.noticeboard.domain.auth.PrincipalDetails;
import com.project.noticeboard.domain.member.Member;
import com.project.noticeboard.domain.member.MemberLoginDto;
import com.project.noticeboard.service.auth.AuthService;
import com.project.noticeboard.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("memberLoginDto") MemberLoginDto form) {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute MemberLoginDto memberLoginDto,
                        BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        log.info("1234567");
        Member loginMember = authService.login(memberLoginDto.getEmail(), memberLoginDto.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "auth/login";
        }

        //sessionManager.createSession(loginMember, response);

        return "redirect:/boards";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/auth/login";
    }

}
