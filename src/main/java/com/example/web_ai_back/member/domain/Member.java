package com.example.web_ai_back.member.domain;

import com.example.web_ai_back.image.domain.Image;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Member {

    @Id
    @Column(name = "MEMBER_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false, length = 30)  // 시각장애이다보니 입력받는 것보다 닉네임 랜덤생성 기능을 넣는것도 not bad...
    private String nickname;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Image> images = new ArrayList<Image>();

//    @Enumerated(EnumType.STRING)
//    private Role role;

    //== pw 암호화 - 해싱 ==//

}
