package com.example.web_ai_back.member.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(nullable = false)
    private String uuid;


    @Column(nullable = false, length = 30)  // 시각장애이다보니 입력받는 것보다 랜덤생성 기능을 넣는것도 not bad...
    private String nickname;

//    @Enumerated(EnumType.STRING)
//    private Role role;

    //== 회원 정보 수정 ==//
//    public void updatePw(String memberPw){
//        this.memberPw = memberPw;
//    }

    //== pw 암호화 - 해싱 ==//

}
