package com.ezen.mood.service;

import com.ezen.mood.config.auth.dto.AuthDto;

import com.ezen.mood.config.auth.dto.SessionMember;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.domain.member.Role;
import com.ezen.mood.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    public void createAccount(AuthDto authDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = authDto.toEntity(passwordEncoder);
        memberRepository.save(member);
    }

    public void withdraw(Member member) {
        memberRepository.delete(member);
    }

    public Member find(Long id) {
        Member member = memberRepository.findById(id).get();
        return member;
    }

//    public Member checkLogin(AuthDto loginDto) {
//        return memberRepository.findByEmail(loginDto.getEmail())
//                .filter(member -> member.getPassword().equals(passwordEncoder.encode(loginDto.getPassword())))
//                .orElse(null);
//
//    }

    public boolean isExist(AuthDto loginDto) {
        return memberRepository.findByEmail(loginDto.getEmail()).isPresent();

    }

    // 로그인 체크.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다: " + email));

        List<GrantedAuthority> authorities = new ArrayList<>();
       // 권한 부여
        authorities.add(new SimpleGrantedAuthority(member.getRoleKey()));
        httpSession.setAttribute("member",new SessionMember(member));
        return new User(member.getEmail(), member.getPassword(), authorities);
    }



}
