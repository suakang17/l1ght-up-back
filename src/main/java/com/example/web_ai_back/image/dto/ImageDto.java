package com.example.web_ai_back.image.dto;

import com.example.web_ai_back.caption.domain.Caption;
import com.example.web_ai_back.gps.Gps;
import com.example.web_ai_back.image.domain.Image;
import com.example.web_ai_back.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ImageDto {

    private Long idx;
    private Long memberIdx;
    private String savedPath;
    private List<Caption> captions;
    private Gps gps;

    @Builder
    public ImageDto(Long idx, Long memberIdx, String savedPath, List<Caption> captions, Gps gps) {
        this.idx = idx;
        this.memberIdx = memberIdx;
        this.savedPath = savedPath;
        this.captions = captions;
        this.gps = gps;
    }
    
    // 사진만 미리 저장해두고 후에 캡션 추가하는 경우
    @Builder ImageDto(Long idx, Long memberIdx, String savedPath, Gps gps) {
        this.idx = idx;
        this.memberIdx = memberIdx;
        this.savedPath = savedPath;
        this.gps = gps;
    }
    
    // update시 수정할 요소가 뭐가 있을지에 대한 고민 필요 - 관리자 - 캡션, 경로 ...
    @Builder
    public ImageDto(List<Caption> captions) {
        this.captions = captions;
    }

    public Image toEntity(Member member) {

        return Image.builder()
                .member(member)
                .gps(gps)
                .savedPath(savedPath)
                .captions(captions)
                .build();
    }
}
