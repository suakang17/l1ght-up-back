package com.example.web_ai_back.imageLogging.domain;

import com.example.web_ai_back.gps.Gps;
import com.example.web_ai_back.imageLogging.dto.ImageLoggingDto;
import com.example.web_ai_back.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ImageLogging {

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

    @CreatedDate
    private Timestamp createdDate;

    public void updateSavedPath(String savedPath) { this.savedPath = savedPath; }


    // imageLoggingEntity -> imgaeLoggingDto method
    public ImageLoggingDto toImageLoggingDTO(ImageLogging imageLogging){

        ImageLoggingDto imageLoggingDto = ImageLoggingDto.builder()
                .memberIdx(imageLogging.getMember().getIdx())
                .idx(imageLogging.getIdx())
                .gps(imageLogging.getGps())
                .savedPath(imageLogging.getSavedPath())
                .factor1(imageLogging.getFactor1())
                .factor2(imageLogging.getFactor2())
                .factor3(imageLogging.getFactor3())
                .factor4(imageLogging.getFactor4())
                .factor5(imageLogging.getFactor5())
                .build();

        return imageLoggingDto;
    }
}