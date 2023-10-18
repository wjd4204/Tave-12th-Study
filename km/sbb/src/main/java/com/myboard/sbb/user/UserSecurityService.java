package com.myboard.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// UserSecurityService 는 스프링 시큐리티 로그인 처리의 핵심 부분
@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // "loadUserByUsername()" 메서드는 사용자명으로 비밀번호를 조회하여 리턴하는 메서드
        // 사용자명으로 SiteUser 객체를 조회하고 만약 사용자명에 해당하는 데이터가 없을 경우에는 UsernameNotFoundException 오류를 발생시킴
        Optional<SiteUser> _siteUser = this.userRepository.findByusername(username);
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        SiteUser siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {  // 사용자명이 "admin" 인 경우 ADMIN 권한을 부여
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {                        // 아니면 USER 권한을 부여
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        // 사용자명, 비번,권한을 입력으로 시큐리티의 User 객체를 생성하여 리턴
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}
