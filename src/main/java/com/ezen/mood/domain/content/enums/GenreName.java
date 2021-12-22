package com.ezen.mood.domain.content.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenreName {
    드라마("drama","드라마"),
    판타지("fantasy","판타지"),
    서부극("western","서부극"),
    공포("horror","호러"),
    로맨스("romance","로맨스"),
    모험("adventure","어드벤쳐"),
    스릴러("thriller","스릴러"),
    느와르("noir","느와르"),
    컬트("cult","컬트"),
    다큐멘터리("documentary","다큐멘터리"),
    코미디("comedy","코미디"),
    가족("family","가족"),
    미스터리("mystery","미스테리"),
    전쟁("war","전쟁"),
    애니메이션("animation","애니메이션"),
    범죄("crime","범죄"),
    뮤지컬("musical","뮤지컬"),
    SF("sf","SF"),
    액션("action","액션"),
    무협("wuxia","무협"),
    에로("erotic","에로"),
    서스펜스("suspense","서스펜스"),
    서사("epic","서사"),
    블랙코미디("blackComedy","블랙코미디");

    private final String key;
    private final String value;
}
