package com.example.web_ai_back.caption.api;

import com.example.web_ai_back.caption.domain.Caption;
import com.example.web_ai_back.caption.repository.CaptionRepository;
import com.example.web_ai_back.image.domain.Image;
import com.example.web_ai_back.image.repository.ImageRepository;
import com.example.web_ai_back.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CaptionApiTest {

    @Autowired
    CaptionRepository captionRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 캡션저장() {
        // given
        Set<String> danger = new HashSet<String>();
        danger.add("car");
        danger.add("traffic light");
        Image image = new Image();

        Caption caption = Caption.builder().originalCaption("there is a car at traffic light ahead.").dangerFactor(danger).image(image).build();

        // when then
        Assertions.assertThat(caption.getOriginalCaption()).isEqualTo("there is a car at traffic light ahead.");
        Assertions.assertThat(caption.getDangerFactor()).isEqualTo(danger);
    }

}