package com.fastcampus.projectboardadmin.dto.security;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SuppressWarnings("unchecked")
public record KakaoOAuth2Response(
        Long id,
        LocalDateTime connectedAt,
        Map<String, Object> properties,
        KakaoAccount kakaoAccount

) {

    public record KakaoAccount (
        Boolean profileNicknameNeedsAgreement,
        Profile profile,
        Boolean hasEmail,
        Boolean emailNeedsAgreement,
        Boolean isEmailValid,
        Boolean isEmailVerified,
        String email
    ) {

        public record Profile(String nickname) {
            public static Profile from(Map<String, Object> attributes) {

                String nickname = String.valueOf(attributes.get("nickname"));

                String[] charSet = {"utf-8", "euc-kr", "ksc5601", "iso-8859-1", "x-windows-949", "utf-16", "ms949"};
                System.out.println("[response_nickname] " + nickname);
                for(int i = 0; i<charSet.length; i++){
                    for(int j = 0; j<charSet.length; j++){
                        try{
                            System.out.println("[" + charSet[i] + "," + charSet[j] + "]" + new String(nickname.getBytes(charSet[i]), charSet[j]));
                        } catch (UnsupportedEncodingException e){
                            e.printStackTrace();
                        }
                    }
                }

                return new Profile(nickname);

            }
        }

        public static KakaoAccount from(Map<String, Object> attributes) {
            return new KakaoAccount(
                    Boolean.valueOf(String.valueOf(attributes.get("profile_nickname_needs_agreement"))),
                    Profile.from((Map<String, Object>) attributes.get("profile")),
                    Boolean.valueOf(String.valueOf(attributes.get("has_email"))),
                    Boolean.valueOf(String.valueOf(attributes.get("email_needs_agreement"))),
                    Boolean.valueOf(String.valueOf(attributes.get("is_email_valid"))),
                    Boolean.valueOf(String.valueOf(attributes.get("is_email_verified"))),
                    String.valueOf(attributes.get("email"))
            );
        }

        public String nickname() { return this.profile().nickname(); }
    }

    public static KakaoOAuth2Response from(Map<String, Object> attributes) {
        return new KakaoOAuth2Response(
                Long.valueOf(String.valueOf(attributes.get("id"))),
                LocalDateTime.parse(
                        String.valueOf(attributes.get("connected_at")),
                        DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault())
                ),
                (Map<String, Object>) attributes.get("properties"),
                KakaoAccount.from((Map<String, Object>) attributes.get("kakao_account"))
        );
    }

    public String email() { return this.kakaoAccount().email(); }
    public String nickname() { return this.kakaoAccount().nickname(); }
}
