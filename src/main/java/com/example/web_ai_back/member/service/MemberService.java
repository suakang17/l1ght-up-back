package com.example.web_ai_back.member.service;

import com.example.web_ai_back.member.domain.Member;
import com.example.web_ai_back.member.dto.MemberDto;
import com.example.web_ai_back.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity signup(MemberDto memberDto) {

        Optional<Member> member = memberRepository.findByIdx(memberDto.getIdx());

        if (member.isEmpty()) {
            Member newMember = Member.builder()
                    .memberPw(memberDto.getMemberPw())
                    .nickname(memberDto.getNickname())
                    .build();

            memberRepository.save(newMember);

            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
    }

    public ResponseEntity login(MemberDto memberDto) {

        Optional<Member> member = memberRepository.findByNickname(memberDto.getNickname());
        Member memberEntity = member.orElse(null);

        if (member == null){
            return new ResponseEntity("존재하지 않는 회원입니다.", HttpStatus.OK);
        }

        if (memberEntity.getMemberPw().equals(memberDto.getMemberPw())){
            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("비밀번호가 일치하지 않습니다.", HttpStatus.OK);
        }

    }
}
