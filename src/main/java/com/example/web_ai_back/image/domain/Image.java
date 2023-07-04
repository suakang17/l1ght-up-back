package com.example.web_ai_back.image.domain;

import com.example.web_ai_back.gps.Gps;
import com.example.web_ai_back.image.dto.ImageDto;
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

    @Embedded
    @Column(nullable = false)
    private Gps gps;

    private int factor1;

    private int factor2;

    private int factor3;

    private int factor4;

    private int factor5;

    public void updateSavedPath(String savedPath) { this.savedPath = savedPath; }


    // imageEntity -> imgaeDto method
    public ImageDto toImageDTO(Image image){

        ImageDto imageDto = ImageDto.builder()
                .memberIdx(image.getMember().getIdx())
                .idx(image.getIdx())
                .gps(image.getGps())
                .savedPath(image.getSavedPath())
                .factor1(image.getFactor1())
                .factor2(image.getFactor2())
                .factor3(image.getFactor3())
                .factor4(image.getFactor4())
                .factor5(image.getFactor5())
                .build();

        return imageDto;
    }

    // imageEntity -> imgaeLoggingDto method
    public ImageLoggingDto imageToImageLoggingDTO(Image image){

        ImageLoggingDto imageLoggingDto = ImageLoggingDto.builder()
                .memberIdx(image.getMember().getIdx())
                .idx(image.getIdx())
                .gps(image.getGps())
                .savedPath(image.getSavedPath())
                .factor1(image.getFactor1())
                .factor2(image.getFactor2())
                .factor3(image.getFactor3())
                .factor4(image.getFactor4())
                .factor5(image.getFactor5())
                .build();

        return imageLoggingDto;
    }
}