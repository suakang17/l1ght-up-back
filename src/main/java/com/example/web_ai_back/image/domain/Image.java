package com.example.web_ai_back.image.domain;

import com.example.web_ai_back.caption.domain.Caption;
import com.example.web_ai_back.gps.Gps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long idx;

    @Column(nullable = false)
    private long memberIdx;

    @Column(nullable = false)
    private String savedPath;

    @Embedded
    @Column(nullable = false)
    private Caption caption;

    @Embedded
    @Column(nullable = false)
    private Gps gps;
}
