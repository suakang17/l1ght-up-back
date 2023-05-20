package com.example.web_ai_back.caption.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Embeddable
public class Caption {

    @Id
    private long idx;

    @Column(nullable = false)
    private String originalCaption;

    @Column(nullable = false)
    @ElementCollection
    private Set<String> dangerFactor;  // 중복될 일 없으니 set로 지정

}
