package com.example.web_ai_back.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MemberDto {
    private String idx;
    private String nickname;
    private String uuid;
}
