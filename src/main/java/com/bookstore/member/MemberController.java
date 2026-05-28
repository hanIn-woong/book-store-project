package com.bookstore.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute MemberDto memberDto, Model model) {
        if (!memberService.isPasswordConfirmed(memberDto)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "member/signup";
        }

        boolean signupSuccess = memberService.signup(memberDto);
        if (!signupSuccess) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
            return "member/signup";
        }

        return "redirect:/member/id-input";
    }

    @GetMapping("/login")
    public String loginRedirect() {
        return "redirect:/member/id-input";
    }

    @GetMapping("/id-input")
    public String idInputForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/id-input";
    }

    @PostMapping("/id-input")
    public String idInput(@ModelAttribute MemberDto memberDto, Model model, HttpSession session) {
        if (memberService.login(memberDto.getUserId(), memberDto.getPassword()).isEmpty()) {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "member/id-input";
        }

        session.setAttribute("loginUserId", memberDto.getUserId());
        return "redirect:/member/mypage";
    }
}
