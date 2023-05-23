package com.example.web_ai_back.image.service;

import com.example.web_ai_back.caption.domain.Caption;
import com.example.web_ai_back.caption.dto.CaptionDto;
import com.example.web_ai_back.caption.repository.CaptionRepository;
import com.example.web_ai_back.image.domain.Image;
import com.example.web_ai_back.image.dto.ImageDto;
import com.example.web_ai_back.image.repository.ImageRepository;
import com.example.web_ai_back.member.domain.Member;
import com.example.web_ai_back.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    private final CaptionRepository captionRepository;

    private final MemberRepository memberRepository;

    public ResponseEntity<ImageDto> save(ImageDto imageDto) {

        Member member = memberRepository.findByIdx(imageDto.getMemberIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. idx=" + imageDto.getMemberIdx()));

        imageRepository.save(imageDto.toEntity(member));

        return new ResponseEntity<>(imageDto, HttpStatus.OK);
    }

    public ResponseEntity<ImageDto> getImageByIdx(Long idx) {

        Image image = imageRepository.findByIdx(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다."));

        ImageDto newimageDto = ImageDto.builder()
                .memberIdx(image.getMember().getIdx())
                .idx(image.getIdx())
                .captions(image.getCaptions())
                .gps(image.getGps())
                .savedPath(image.getSavedPath())
                .build();

        return new ResponseEntity<>(newimageDto, HttpStatus.OK);
    }

    public ResponseEntity<ImageDto> update(Long idx, ImageDto imageDto) {

        Image image = imageRepository.findByIdx(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다. idx=" + idx));

        // 캡션 수정
        image.updateCaption(imageDto.getCaptions());  // jpa사용시 영속성 컨텍스트 유지가 되므로 entity값만 변경해도 db 반영됨(별도 update query 필요x)
        
        // 저장 경로 수정
//        image.updateSavedPath(imageDto.getSavedPath());

        ImageDto updatedImageDto = ImageDto.builder()
                .captions(image.getCaptions())
                .build();

        return new ResponseEntity<>(updatedImageDto, HttpStatus.OK);
    }
}
