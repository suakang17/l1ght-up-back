package com.example.web_ai_back.caption.service;

import com.example.web_ai_back.caption.domain.Caption;
import com.example.web_ai_back.caption.dto.CaptionDto;
import com.example.web_ai_back.caption.repository.CaptionRepository;
import com.example.web_ai_back.image.domain.Image;
import com.example.web_ai_back.image.dto.ImageDto;
import com.example.web_ai_back.image.repository.ImageRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CaptionService {

    private final CaptionRepository captionRepository;

    private final ImageRepository imageRepository;

    public ResponseEntity<CaptionDto> save(CaptionDto captionDto) {

        Image image = imageRepository.findByIdx(captionDto.getImageIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다. idx=" + captionDto.getImageIdx()));

        captionRepository.save(captionDto.toEntity(image));

        return new ResponseEntity<>(captionDto, HttpStatus.OK);
    }

    public ResponseEntity<CaptionDto> getCaptionByIdx(Long idx) {

        Caption caption = captionRepository.findByIdx(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 캡션이 존재하지 않습니다. idx=" + idx));

        CaptionDto newCaptionDto = CaptionDto.builder()
                .idx(caption.getIdx())
                .imageIdx(caption.getImage().getIdx())
                .originalCaption(caption.getOriginalCaption())
                .dangerFactor(caption.getDangerFactor())
                .build();

        return new ResponseEntity<>(newCaptionDto, HttpStatus.OK);
    }

    public ResponseEntity<CaptionDto> update(Long idx, CaptionDto captionDto) {

        Caption caption = captionRepository.findByIdx(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 캡션이 존재하지 않습니다. idx=" + idx));

        caption.update(captionDto.getOriginalCaption(), captionDto.getDangerFactor());  // jpa사용시 영속성 컨텍스트 유지가 되므로 entity값만 변경해도 db 반영됨(별도 update query 필요x)

        CaptionDto updatedCaptionDto = CaptionDto.builder()
                .originalCaption(caption.getOriginalCaption())
                .dangerFactor(caption.getDangerFactor())
                .build();

        return new ResponseEntity<>(updatedCaptionDto, HttpStatus.OK);
    }

    public Caption toEntityWhileSaveImage(Image image, String originalCaption) {

        return Caption.builder()
                .image(image)
                .originalCaption(originalCaption)
                .build();
    }

    public List<Caption> toEntityList(List<String> captions, ImageDto imageDto) {

        Image image = imageRepository.findByIdx(imageDto.getIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다. idx=" + imageDto.getIdx()));

        List<Caption> newCaptionList = captions.stream().map(o -> toEntityWhileSaveImage(image, o)).collect(Collectors.toList());

        return newCaptionList;
    }

    public List<CaptionDto> toDtoList(List<Caption> captions) {

        List<CaptionDto> captionDtoList = captions.stream().map(o -> toDto(o)).collect(Collectors.toList());

        return captionDtoList;
    }

    private CaptionDto toDto(Caption o) {

        return CaptionDto.builder()
                .originalCaption(o.getOriginalCaption())
                .dangerFactor(o.getDangerFactor())
                .imageIdx(o.getImage().getIdx())
                .idx(o.getIdx())
                .build();
    }

    public List<String> toStringList(List<CaptionDto> captionDtoList) {

        List<String> stringCaptionList = captionDtoList.stream().map(o -> o.getOriginalCaption().toString()).collect(Collectors.toList());

        return stringCaptionList;
    }
}
