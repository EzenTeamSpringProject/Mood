package com.ezen.mood.service;

import com.ezen.mood.config.auth.dto.LoginDto;

import com.ezen.mood.domain.member.Member;
import com.ezen.mood.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public void save(Member member) {
        memberRepository.save(member);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }

    public Member find(Long id) {
        Member member = memberRepository.findById(id).get();
        return member;
    }

    public Member checkLogin(LoginDto loginDto) {
        return memberRepository.findByEmail(loginDto.getEmail())
                .filter(member -> member.getPassword().equals(loginDto.getPassword()))
                .orElse(null);

    }


}
