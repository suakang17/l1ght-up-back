package com.example.web_ai_back.member.repository;

import com.example.web_ai_back.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
