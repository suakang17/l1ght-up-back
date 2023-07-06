package com.example.web_ai_back.image.repository;

import com.example.web_ai_back.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByIdx(Long idx);

    // 특정 멤버의 사진은 데이터로써 가치가 좋다, 나쁘다 ... 의 연장선을 위한 메서드
    Optional<Image> findByMemberIdx(Long idx);

    @Override
    <S extends Image> S save(S entity);
}