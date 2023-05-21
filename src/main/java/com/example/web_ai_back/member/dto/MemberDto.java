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
    
    // memberApi는 오직 회원가입 용도이며 이 때의 dto이므로 image(연관관계) 필드는 넣지 않아도 됨
}
