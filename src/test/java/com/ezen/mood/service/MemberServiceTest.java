package com.ezen.mood.service;

import com.ezen.mood.domain.member.Member;
import com.ezen.mood.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Rollback(value = false)
@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Member member = Member.builder()
                .name("dokuny")
                .email("asdas@naver.com")
                .build();



        Member findMember = memberService.find(member.getId());

        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        Assertions.assertThat(member.getEmail()).isEqualTo(findMember.getEmail());
        Assertions.assertThat(member.getRole()).isEqualTo(findMember.getRole());

    }

    @Test
    void delete(){
        Member member = Member.builder()
                .name("dokuny")
                .email("asdas@naver.com")
                .build();




        Member findMember = memberService.find(member.getId());
        memberService.withdraw(findMember);

        List<Member> all = memberRepository.findAll();

        Assertions.assertThat(all.size()).isEqualTo(0);

    }

}