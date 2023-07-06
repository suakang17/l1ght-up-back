package com.example.web_ai_back.imageLogging.repository;

import com.example.web_ai_back.imageLogging.domain.ImageLogging;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageLoggingRepository extends JpaRepository<ImageLogging, Long> {

    Optional<ImageLogging> findByIdx(Long idx);

    // 특정 멤버의 사진은 데이터로써 가치가 좋다, 나쁘다 ... 의 연장선을 위한 메서드
    Optional<ImageLogging> findByMemberIdx(Long idx);

    @Override
    <S extends ImageLogging> S save(S entity);
}