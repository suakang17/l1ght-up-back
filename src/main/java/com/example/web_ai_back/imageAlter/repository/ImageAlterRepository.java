package com.example.web_ai_back.imageAlter.repository;

import com.example.web_ai_back.imageAlter.domain.ImageAlter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageAlterRepository extends JpaRepository<ImageAlter, Long> {

    Optional<ImageAlter> findByIdx(Long idx);

    // 특정 멤버의 사진은 데이터로써 가치가 좋다, 나쁘다 ... 의 연장선을 위한 메서드
    Optional<ImageAlter> findByMemberIdx(Long idx);

    @Override
    <S extends ImageAlter> S save(S entity);
}