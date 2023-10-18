package com.myboard.sbb;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration  // 스프링의 환경설정 파일임을 의미하는 어노테이션
@EnableWebSecurity  // 내부적으로 SpringSecurityFilterChain이 동작하여 URL 필터가 동작한다.
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 모든 인증되지 않은 요청을 허락한다는 의미이다.
        // 따라서 로그인을 하지 않더라도 모든 페이지에 접근할 수 있다.
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**"))
                        .permitAll())
                        .csrf((csrf) -> csrf.ignoringRequestMatchers    // csrf 비활성화
                                (new AntPathRequestMatcher("/h2-console/**")))
                        .headers((headers) -> headers         // frame 구조 허용
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                        .formLogin((formLogin) -> formLogin     // 시큐리티의 로그인 설정을 담당하는 부분으로
                        .loginPage("/user/login")               // 로그인 페이지의 URL 은 왼쪽과 같고
                        .defaultSuccessUrl("/"))      // 로그인 성공시에 이동하는 디폴트 페이지는 루트 URL 임을 의미한다
                        .logout((logout) -> logout      // 로그아웃을 위한 설정 추가
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")      // 로그아웃이 성공하면 루트페이지("/")로 이동하게 함
                        .invalidateHttpSession(true));  // 로그아웃시 생성된 사용자 세션도 삭제하도록 처리
        return http.build();
    }

    // PasswordEncoder 빈(bean)을 생성
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // AuthenticationManager 는 시큐리티의 인증을 담당한다.
    // 사용자 인증시 앞에서 작성한 UserSecurityService 와 PasswordEncoder를 사용한다.
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
