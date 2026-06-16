package com.bookstore.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        Member member = memberService.login(memberDto.getUserId(), memberDto.getPassword()).orElse(null);
        if (member == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "member/id-input";
        }

        session.setAttribute("loginUserId", member.getUserId());
        session.setAttribute("loginRole", member.getRole());

        if ("ADMIN".equals(member.getRole())) {
            return "redirect:/admin";
        }

        return "redirect:/books";
    }

    @GetMapping("/mypage")
    public String myPage(@RequestParam(required = false) String userId, Model model, HttpSession session) {
        userId = resolveLoginUserId(userId, session);

        if (userId == null) {
            return "redirect:/member/id-input";
        }

        model.addAttribute("member", memberService.findByUserId(userId).orElse(null));
        return "member/mypage";
    }

    @GetMapping("/mypage/edit")
    public String editMyPageForm(@RequestParam(required = false) String userId, Model model, HttpSession session) {
        userId = resolveLoginUserId(userId, session);

        if (userId == null) {
            return "redirect:/member/id-input";
        }

        Member member = memberService.findByUserId(userId).orElse(null);
        if (member == null) {
            model.addAttribute("member", null);
            return "member/mypage";
        }

        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(member.getUserId());
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        model.addAttribute("memberDto", memberDto);
        return "member/mypage-edit";
    }

    @PostMapping("/mypage/edit")
    public String editMyPage(@ModelAttribute MemberDto memberDto, Model model) {
        if (memberService.hasPasswordInput(memberDto) && !memberService.isPasswordConfirmed(memberDto)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "member/mypage-edit";
        }

        memberService.updateProfile(memberDto);
        return "redirect:/member/mypage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/id-input";
    }

    private String resolveLoginUserId(String userId, HttpSession session) {
        if (userId != null && !userId.isBlank()) {
            return userId;
        }

        Object loginUserId = session.getAttribute("loginUserId");
        return loginUserId == null ? null : loginUserId.toString();
    }
}
