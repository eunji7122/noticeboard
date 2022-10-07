package com.project.noticeboard.web.member;

import com.project.noticeboard.domain.member.Member;
import com.project.noticeboard.domain.member.Role;
import com.project.noticeboard.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("member") Member member) {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signupPost(@Valid @ModelAttribute Member member,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.reject("signupFail", "정보를 입력해 주세요.");
            return "auth/signup";
        }

        String encodePwd = bCryptPasswordEncoder.encode(member.getPassword());
        member.setPassword(encodePwd);

        member.setRole(Role.USER);
        memberService.save(member);

        return "redirect:/auth/login";
    }
}
