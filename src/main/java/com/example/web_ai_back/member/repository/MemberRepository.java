package com.example.web_ai_back.member.repository;

import com.example.web_ai_back.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByIdx(String idx);

    @Override
    <S extends Member> S save(S entity);

    @Override
    void delete(Member entity);

    Optional<Member> findByNickname(String nickname);
}
