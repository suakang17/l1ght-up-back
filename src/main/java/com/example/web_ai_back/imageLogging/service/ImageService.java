package com.example.web_ai_back.imageLogging.service;

import com.example.web_ai_back.imageLogging.dto.ImageDto;
import com.example.web_ai_back.imageLogging.domain.Image;
import com.example.web_ai_back.imageLogging.repository.ImageRepository;
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

    private final MemberRepository memberRepository;

    public ResponseEntity<ImageDto> save(ImageDto imageDto) {

        Member member = memberRepository.findByIdx(imageDto.getMemberIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. idx=" + imageDto.getMemberIdx()));

        Image newImage = imageRepository.save(imageDto.toEntity(member));

        ImageDto newImageDto = newImage.toDTO(newImage);

        return new ResponseEntity<>(newImageDto, HttpStatus.OK);
    }

    public ResponseEntity<ImageDto> getImageByIdx(Long idx) {

        Image image = imageRepository.findByIdx(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다."));

        ImageDto imageDto = image.toDTO(image);

        return new ResponseEntity<>(imageDto, HttpStatus.OK);
    }

}