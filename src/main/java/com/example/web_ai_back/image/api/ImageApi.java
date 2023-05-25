package com.example.web_ai_back.image.api;

import com.example.web_ai_back.image.dto.ImageDto;
import com.example.web_ai_back.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageApi {

    private final ImageService imageService;

    @PostMapping("/create")
    public ResponseEntity<ImageDto> imageCreate(@RequestBody ImageDto imageDto) {
        return imageService.save(imageDto);
    }

    @GetMapping("/imageIdx")
    public ResponseEntity<ImageDto> getImageByIdx(@RequestParam Long idx) {
        return imageService.getImageByIdx(idx);
    }

//    @PutMapping("/imageIdx")
//    public ResponseEntity<ImageDto> imageUpdate(@RequestBody ImageDto imageDto) {
//        return imageService.update(imageDto);
//    }
}