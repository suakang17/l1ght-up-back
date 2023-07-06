package com.example.web_ai_back.member.api;

import com.example.web_ai_back.member.domain.Member;
import com.example.web_ai_back.member.dto.MemberDto;
import com.example.web_ai_back.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity memberSignup(@RequestBody MemberDto memberDto) {
        return memberService.signup(memberDto);
    }

}
