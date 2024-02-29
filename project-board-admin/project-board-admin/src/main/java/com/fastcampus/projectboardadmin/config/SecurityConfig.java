package com.fastcampus.projectboardadmin.config;

//import com.fastcampus.projectboard.dto.security.BoardPrincipal;
//import com.fastcampus.projectboard.dto.security.KakaoOAuth2Response;
//import com.fastcampus.projectboard.service.UserAccountService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        // static resource: css, js...
//        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }

    // 실제 유저의 인증 정보를 가져오는 빈
//    @Bean
//    public UserDetailsService userDetailsService(UserAccountService userAccountService) {
//        return username -> userAccountService
//                .searchUser(username)
//                .map(BoardPrincipal::from)
//                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - username"));
//    }
//
//    @Bean
//    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(
//            UserAccountService userAccountService,
//            PasswordEncoder passwordEncoder
//    ) {
//        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
//
//        return userRequest -> {
//            OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//            KakaoOAuth2Response kakaoResponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());
//            String registrationId = userRequest.getClientRegistration().getRegistrationId();        // 예상값: kakao
//            String providerId = String.valueOf(kakaoResponse.id());
//            String username = registrationId + "_" + providerId;
//            String dummyPassword = passwordEncoder.encode("{bcrypt}" + UUID.randomUUID());
//
//            return userAccountService.searchUser(username)
//                    .map(BoardPrincipal::from)
//                    .orElseGet(() ->
//                                BoardPrincipal.from(
//                                        userAccountService.saveUser(
//                                                username,
//                                                dummyPassword,
//                                                kakaoResponse.email(),
//                                                kakaoResponse.nickname(),
//                                                null
//                                        )
//                                )
//                            );
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
