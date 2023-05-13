package com.example.web_ai_back.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(nullable = false, unique = true, length = 30)
    private String memberId;

    @Column(nullable = false)
    private String memberPw;

    @Column(nullable = false, length = 30)
    private String nickName;

    @Enumerated(EnumType.STRING)
    private Role role;

    //== 회원 정보 수정 ==//
    public void updatePw(String memberPw){
        this.memberPw = memberPw;
    }

    //== pw 암호화 ==//

}
