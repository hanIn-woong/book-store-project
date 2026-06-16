package com.bookstore.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean signup(MemberDto memberDto) {
        if (memberRepository.existsByUserId(memberDto.getUserId())) {
            return false;
        }

        Member member = new Member(
                memberDto.getUserId(),
                memberDto.getPassword(),
                memberDto.getName(),
                memberDto.getEmail(),
                "USER"
        );
        memberRepository.save(member);
        return true;
    }

    public boolean isPasswordConfirmed(MemberDto memberDto) {
        return memberDto.getPassword() != null
                && memberDto.getPassword().equals(memberDto.getPasswordConfirm());
    }

    public boolean hasPasswordInput(MemberDto memberDto) {
        return memberDto.getPassword() != null && !memberDto.getPassword().isBlank();
    }

    public Optional<Member> login(String userId, String password) {
        return memberRepository.findByUserId(userId)
                .filter(member -> member.getPassword().equals(password));
    }

    public Optional<Member> findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    public void updateProfile(MemberDto memberDto) {
        memberRepository.updateProfile(
                memberDto.getUserId(),
                memberDto.getName(),
                memberDto.getEmail()
        );

        if (hasPasswordInput(memberDto)) {
            memberRepository.updatePassword(memberDto.getUserId(), memberDto.getPassword());
        }
    }
}
