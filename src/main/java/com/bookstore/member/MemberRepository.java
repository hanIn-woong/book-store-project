package com.bookstore.member;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    private final List<Member> members = new ArrayList<>();

    public MemberRepository() {
        members.add(new Member("admin", "admin", "관리자", "admin@bookstore.com", "ADMIN"));
    }

    public void save(Member member) {
        members.add(member);
    }

    public void updateProfile(String userId, String name, String email) {
        findByUserId(userId).ifPresent(member -> {
            member.setName(name);
            member.setEmail(email);
        });
    }

    public void updatePassword(String userId, String password) {
        findByUserId(userId).ifPresent(member -> member.setPassword(password));
    }

    public void deleteByUserId(String userId) {
        members.removeIf(member -> member.getUserId().equals(userId));
    }

    public Optional<Member> findByUserId(String userId) {
        return members.stream()
                .filter(member -> member.getUserId().equals(userId))
                .findFirst();
    }

    public boolean existsByUserId(String userId) {
        return findByUserId(userId).isPresent();
    }
}
