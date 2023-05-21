package com.example.web_ai_back.caption.dto;

import com.example.web_ai_back.caption.domain.Caption;
import com.example.web_ai_back.image.domain.Image;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public class CaptionDto {

    private Long idx;
    private Long imageIdx;  // 전체 객체 요청을 하므로 전체 img객체가 아닌 id값만 가져와 매핑하도록
    private String originalCaption;
    private Set<String> dangerFactor;

    @Builder
    public CaptionDto(Long idx, Long imageIdx, String originalCaption, Set<String> dangerFactor) {
        this.idx = idx;
        this.imageIdx = imageIdx;
        this.originalCaption = originalCaption;
        this.dangerFactor = dangerFactor;
    }

    @Builder
    public CaptionDto(String originalCaption, Set<String> dangerFactor) {
        this.originalCaption = originalCaption;
        this.dangerFactor = dangerFactor;
    }

    public Caption toEntity(Image image) {

        return Caption.builder()
                .image(image)
                .originalCaption(originalCaption)
                .dangerFactor(dangerFactor)
                .build();
    }

}
