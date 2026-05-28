package com.bookstore.member;

import lombok.Data;

@Data
public class MemberDto {
    private String userId;
    private String password;
    private String passwordConfirm;
    private String name;
    private String email;
}
