package com.project.noticeboard.web.member;

import com.project.noticeboard.domain.member.Member;
import com.project.noticeboard.service.member.MemberServiceImpl;
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
public class MemberController {

    private final MemberServiceImpl memberService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(@Valid @ModelAttribute Member member,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/signup";
        }

        memberService.save(member);
        return "redirect:/login";
    }
}
