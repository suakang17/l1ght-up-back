package com.example.web_ai_back.caption.repository;

import com.example.web_ai_back.caption.domain.Caption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CaptionRepository extends JpaRepository<Caption, Long> {

    @Override
    <S extends Caption> S save(S entity);

    @Override
    <S extends Caption> List<S> saveAll(Iterable<S> entities);

    Optional<Caption> findByIdx(Long idx);

    List<Caption> findByImage(Long imageIdx);
}
