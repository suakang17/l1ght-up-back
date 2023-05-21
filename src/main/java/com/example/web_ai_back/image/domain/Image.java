package com.example.web_ai_back.image.domain;

import com.example.web_ai_back.caption.domain.Caption;
import com.example.web_ai_back.gps.Gps;
import com.example.web_ai_back.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @Column(name = "IMAGE_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "MEMBER_IDX")
    private Member member;

    @Column(nullable = false)
    private String savedPath;

    @OneToMany(mappedBy = "image")  // image가 연관관계 주인이 아님을 mappedBy로 선언
    @Builder.Default
    private List<Caption> captions = new ArrayList<Caption>();

    @Embedded
    @Column(nullable = false)
    private Gps gps;

    // 연관관계 설정
    public void setMember(Member member) {
        this.member = member;
    }
}
