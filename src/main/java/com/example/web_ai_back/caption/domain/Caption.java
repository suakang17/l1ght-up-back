package com.example.web_ai_back.caption.domain;

import com.example.web_ai_back.image.domain.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Caption {

    @Id
    @Column(name = "CAPTION_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "IMAGE_IDX")  // img 외래키를 가짐 -> 연관관계 주인
    private Image image;

    @Column(nullable = false)
    private String originalCaption;

    @Column(nullable = false)
    @ElementCollection
    private Set<String> dangerFactor;  // 중복될 일 없으니 set로 지정

    public void update(String originalCaption, Set<String> dangerFactor) {
        this.originalCaption = originalCaption;
        this.dangerFactor = dangerFactor;
    }
}
