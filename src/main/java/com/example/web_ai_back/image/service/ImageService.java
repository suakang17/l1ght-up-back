package com.example.web_ai_back.image.service;

import com.example.web_ai_back.caption.domain.Caption;
import com.example.web_ai_back.caption.dto.CaptionDto;
import com.example.web_ai_back.caption.repository.CaptionRepository;
import com.example.web_ai_back.caption.service.CaptionService;
import com.example.web_ai_back.image.domain.Image;
import com.example.web_ai_back.image.dto.ImageDto;
import com.example.web_ai_back.image.repository.ImageRepository;
import com.example.web_ai_back.member.domain.Member;
import com.example.web_ai_back.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    private final CaptionRepository captionRepository;

    private final MemberRepository memberRepository;

    private final CaptionService captionService;

    public ResponseEntity<ImageDto> save(ImageDto imageDto) {

        Member member = memberRepository.findByIdx(imageDto.getMemberIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. idx=" + imageDto.getMemberIdx()));

        Image newImage = imageRepository.save(imageDto.toEntity(member));
        if (imageDto.getCaptions() == null || imageDto.getCaptions().isEmpty()) {
            return new ResponseEntity<>(imageDto, HttpStatus.OK);

        } else { captionRepository.saveAll(captionService.toEntityList(imageDto.getCaptions(), newImage.getIdx()));
        }

        return new ResponseEntity<>(imageDto, HttpStatus.OK);
    }

    public ResponseEntity<ImageDto> getImageByIdx(Long idx) {

        Image image = imageRepository.findByIdx(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다."));


        List<CaptionDto> captionDtoList = captionService.toDtoList(image.getCaptions());

        ImageDto newImageDto = ImageDto.builder()
                .memberIdx(image.getMember().getIdx())
                .idx(image.getIdx())
                .captions(captionService.toStringList(captionDtoList))
                .gps(image.getGps())
                .savedPath(image.getSavedPath())
                .build();

        return new ResponseEntity<>(newImageDto, HttpStatus.OK);
    }

    public ResponseEntity<ImageDto> update(ImageDto imageDto) {

        Image image = imageRepository.findByIdx(imageDto.getIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다. idx=" + imageDto.getIdx()));

        // 캡션 수정, update
        image.updateCaption(captionRepository.findByImage(image.getIdx()));  // jpa사용시 영속성 컨텍스트 유지가 되므로 entity값만 변경해도 db 반영됨(별도 update query 필요x)
        
        // 저장 경로 수정
//        image.updateSavedPath(imageDto.getSavedPath());
        List<CaptionDto> captionDtoList = captionService.toDtoList(image.getCaptions());

        ImageDto updatedImageDto = ImageDto.builder()
                .captions(captionService.toStringList(captionDtoList))
                .build();

        return new ResponseEntity<>(updatedImageDto, HttpStatus.OK);
    }
}
