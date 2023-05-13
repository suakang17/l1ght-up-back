package com.example.web_ai_back.member.api;

import com.example.web_ai_back.member.domain.Member;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @RequestMapping("/signup")
    public String signUp(Member member) {

    }
}
