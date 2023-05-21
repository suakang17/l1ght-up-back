package com.example.web_ai_back.caption.api;

import com.example.web_ai_back.caption.domain.Caption;
import com.example.web_ai_back.caption.dto.CaptionDto;
import com.example.web_ai_back.caption.service.CaptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/caption")
@RequiredArgsConstructor
public class CaptionApi {

    private final CaptionService captionService;

    @PostMapping("/create")
    public ResponseEntity<CaptionDto> captionCreate(@RequestBody CaptionDto captionDto) {
        return captionService.save(captionDto);
    }

    @GetMapping("/captionIdx")
    public ResponseEntity<CaptionDto> getCaptionByIdx(@RequestParam Long idx) {
        return captionService.getCaptionByIdx(idx);
    }

    @PutMapping("/captionIdx")
    public ResponseEntity<CaptionDto> captionUpdate(@RequestParam Long idx, @RequestBody CaptionDto captionDto) {
        return captionService.update(idx, captionDto);
    }

}
