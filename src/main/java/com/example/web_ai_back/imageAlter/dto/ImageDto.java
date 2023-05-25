package com.example.web_ai_back.imageAlter.dto;

import com.example.web_ai_back.gps.Gps;
import com.example.web_ai_back.imageAlter.domain.Image;
import com.example.web_ai_back.member.domain.Member;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "ImageDtoBuilder", toBuilder = true)
@JsonDeserialize(builder = ImageDto.ImageDtoBuilder.class)
public class ImageDto {

    private Long idx;
    private Long memberIdx;
    private String savedPath;
    private Gps gps;

    private int factor1;
    private int factor2;
    private int factor3;
    private int factor4;
    private int factor5;


    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageDtoBuilder {

    }

    @Builder
    public ImageDto(Long idx, Long memberIdx, String savedPath, Gps gps, int factor1, int factor2, int factor3, int factor4, int factor5) {
        this.idx = idx;
        this.memberIdx = memberIdx;
        this.savedPath = savedPath;
        this.gps = gps;
        this.factor1 = factor1;
        this.factor2 = factor2;
        this.factor3 = factor3;
        this.factor4 = factor4;
        this.factor5 = factor5;
    }

    // update시 수정할 요소 생성자 필요


    // imageDto -> imgaeEntity method
    public Image toEntity(Member member) {

        return Image.builder()
                .member(member)
                .savedPath(savedPath)
                .gps(gps)
                .factor1(factor1)
                .factor2(factor2)
                .factor3(factor3)
                .factor4(factor4)
                .factor5(factor5)
                .build();
    }
}