package com.example.web_ai_back.imageLogging.service;

import com.example.web_ai_back.imageLogging.dto.ImageLoggingDto;
import com.example.web_ai_back.imageLogging.domain.ImageLogging;
import com.example.web_ai_back.imageLogging.repository.ImageLoggingRepository;
import com.example.web_ai_back.member.domain.Member;
import com.example.web_ai_back.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageLoggingService {

    private final ImageLoggingRepository imageLoggingRepository;

    private final MemberRepository memberRepository;

    public ResponseEntity<ImageLoggingDto> save(ImageLoggingDto imageLoggingDto) {

        Member member = memberRepository.findByIdx(imageLoggingDto.getMemberIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. idx=" + imageLoggingDto.getMemberIdx()));

        ImageLogging newImageLogging = imageLoggingRepository.save(imageLoggingDto.toImageLoggingEntity(member));

        ImageLoggingDto newImageLoggingDto = newImageLogging.toImageLoggingDTO(newImageLogging);

        return new ResponseEntity<>(newImageLoggingDto, HttpStatus.OK);
    }

    public ResponseEntity<ImageLoggingDto> getImageByIdx(Long idx) {

        ImageLogging image = imageLoggingRepository.findByIdx(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다."));

        ImageLoggingDto imageLoggingDto = image.toImageLoggingDTO(image);

        return new ResponseEntity<>(imageLoggingDto, HttpStatus.OK);
    }

}