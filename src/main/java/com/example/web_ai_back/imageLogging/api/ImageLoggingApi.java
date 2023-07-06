package com.example.web_ai_back.imageLogging.api;

import com.example.web_ai_back.imageLogging.dto.ImageLoggingDto;
import com.example.web_ai_back.imageLogging.service.ImageLoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/imageLogging")
@RequiredArgsConstructor
public class ImageLoggingApi {

    private final ImageLoggingService imageLoggingService;

    @PostMapping("/create")
    public ResponseEntity<ImageLoggingDto> imageCreate(@RequestBody ImageLoggingDto imageLoggingDto) {
        return imageLoggingService.save(imageLoggingDto);
    }

    @GetMapping("/imageIdx")
    public ResponseEntity<ImageLoggingDto> getImageByIdx(@RequestParam Long idx) {
        return imageLoggingService.getImageByIdx(idx);
    }

//    @PutMapping("/imageIdx")
//    public ResponseEntity<ImageDto> imageUpdate(@RequestBody ImageDto imageDto) {
//        return imageService.update(imageDto);
//    }
}