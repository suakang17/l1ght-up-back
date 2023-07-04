package com.example.web_ai_back.imageAlter.service;

import com.example.web_ai_back.imageAlter.domain.ImageAlter;
import com.example.web_ai_back.imageAlter.dto.ImageAlterDto;
import com.example.web_ai_back.imageAlter.repository.ImageAlterRepository;
import com.example.web_ai_back.imageLogging.api.ImageLoggingApi;
import com.example.web_ai_back.imageLogging.dto.ImageLoggingDto;
import com.example.web_ai_back.member.domain.Member;
import com.example.web_ai_back.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageAlterService {

    private final ImageAlterRepository imageAlterRepository;

    private final MemberRepository memberRepository;
    private final ImageLoggingApi imageLoggingApi;


    public ResponseEntity<ImageAlterDto> save(ImageAlterDto imageAlterDto) {

        Member member = memberRepository.findByIdx(imageAlterDto.getMemberIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. idx=" + imageAlterDto.getMemberIdx()));

        ImageAlter newImageAlter = imageAlterRepository.save(imageAlterDto.toImageAlterEntity(member));

        ImageAlterDto newImageAlterDto = newImageAlter.toImageAlterDTO(newImageAlter);

        ImageLoggingDto newImageLoggingDto = newImageAlter.imageAlterToImageLoggingDTO(newImageAlter);

        imageLoggingApi.imageCreate(newImageLoggingDto);

        return new ResponseEntity<>(newImageAlterDto, HttpStatus.OK);
    }

    public ResponseEntity<ImageAlterDto> getImageByIdx(Long idx) {

        ImageAlter imageAlter = imageAlterRepository.findByIdx(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다."));

        ImageAlterDto imageAlterDto = imageAlter.toImageAlterDTO(imageAlter);

        return new ResponseEntity<>(imageAlterDto, HttpStatus.OK);
    }

}