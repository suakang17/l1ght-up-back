package com.example.web_ai_back.imageAlter.domain;

import com.example.web_ai_back.gps.Gps;
import com.example.web_ai_back.image.domain.Image;
import com.example.web_ai_back.imageAlter.dto.ImageAlterDto;
import com.example.web_ai_back.imageLogging.dto.ImageLoggingDto;
import com.example.web_ai_back.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ImageAlter {

    @Id
    @Column(name = "IMAGE_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "MEMBER_IDX")
    private Member member;

    @Column(nullable = false)
    private String savedPath;

    @Embedded
    @Column(nullable = false)
    private Gps gps;

    private String originalCaption;

    private int factor1;

    private int factor2;

    private int factor3;

    private int factor4;

    private int factor5;

    public void updateSavedPath(String savedPath) { this.savedPath = savedPath; }


    // imageAlterEntity -> imgaeAlterDto method
    public ImageAlterDto toImageAlterDTO(ImageAlter imageAlter){

        ImageAlterDto imageAlterDto = ImageAlterDto.builder()
                .memberIdx(imageAlter.getMember().getIdx())
                .idx(imageAlter.getIdx())
                .gps(imageAlter.getGps())
                .originalCaption(imageAlter.getOriginalCaption())
                .savedPath(imageAlter.getSavedPath())
                .factor1(imageAlter.getFactor1())
                .factor2(imageAlter.getFactor2())
                .factor3(imageAlter.getFactor3())
                .factor4(imageAlter.getFactor4())
                .factor5(imageAlter.getFactor5())
                .build();

        return imageAlterDto;
    }

    // imageAlterEntity -> imgaeLoggingDto method
    public ImageLoggingDto imageAlterToImageLoggingDTO(ImageAlter imageAlter){

        ImageLoggingDto imageLoggingDto = ImageLoggingDto.builder()
                .memberIdx(imageAlter.getMember().getIdx())
                .idx(imageAlter.getIdx())
                .gps(imageAlter.getGps())
                .originalCaption(imageAlter.getOriginalCaption())
                .savedPath(imageAlter.getSavedPath())
                .factor1(imageAlter.getFactor1())
                .factor2(imageAlter.getFactor2())
                .factor3(imageAlter.getFactor3())
                .factor4(imageAlter.getFactor4())
                .factor5(imageAlter.getFactor5())
                .build();

        return imageLoggingDto;
    }
}