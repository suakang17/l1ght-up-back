package com.example.web_ai_back.image.service;

import com.example.web_ai_back.caption.repository.CaptionRepository;
import com.example.web_ai_back.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    private final CaptionRepository captionRepository;
}
