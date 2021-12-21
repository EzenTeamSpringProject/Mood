package com.ezen.mood.config.auth;

import com.ezen.mood.config.auth.dto.OAuthAttributes;
import com.ezen.mood.config.auth.dto.SessionMember;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;


@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate =new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        // 타 서비스 로그인 정보로 구성된 내 서비스에 필요한 정보가 일단 여기에 담긴다.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId,userNameAttributeName,oAuth2User.getAttributes());

        // 밑에 설명 적어놓음
        Member member = saveOrUpdate(attributes);

        // 세션에 담아줌
        httpSession.setAttribute("member",new SessionMember(member));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),attributes.getAttributes(),attributes.getNameAttributeKey());
    }

    // 구글 로그인으로 가입한 회원이 데이터베이스에 있으면 정보를 가져와서 변경사항이 있다면 갱신 해주고, 데이터베이스에 없는 신규 회원이면 새로 만들어준다.
    private Member saveOrUpdate(OAuthAttributes attributes){
        Member member = memberRepository.findByEmail(attributes.getEmail()).map(entity->entity.updateRelatedSns(attributes.getName(),attributes.getPicture())).orElse(attributes.toEntity());
        return memberRepository.save(member);

    }
}
