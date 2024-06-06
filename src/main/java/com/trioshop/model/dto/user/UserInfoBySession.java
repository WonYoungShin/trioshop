package com.trioshop.model.dto.user;

import com.trioshop.utils.service.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 *     private final Long userCode;
 *     private final Long gradeCode;
 *     private final String userId;
 *     private final String userNickname;
 *     private final String userPasswd;  -- 인증시에만 사용하고 인증이 끝나면 ""로 초기화
 *     private Role role; -- 시큐리티 Role 할당, 확인을 위한 필드 (생성자 호출시 자동 호출)
 */

@Getter
@Builder
@ToString
public class UserInfoBySession implements UserDetails {
    private final Long userCode;
    private final Long gradeCode;
    private final String userId;
    private final String userNickname;
    private final String userPasswd;
    private Role role;

    public UserInfoBySession(Long userCode, Long gradeCode, String userId, String userNickname, String userPasswd) {
        this.userCode = userCode;
        this.gradeCode = gradeCode;
        this.userId = userId;
        this.userNickname = userNickname;
        this.userPasswd = userPasswd;
        setRole();
    }

    //비밀번호 없애기 위한 빌더 생성자
    @Builder
    public UserInfoBySession(Long userCode, Long gradeCode, String userId, String userNickname, String userPasswd, Role role) {
        this.userCode = userCode;
        this.gradeCode = gradeCode;
        this.userId = userId;
        this.userNickname = userNickname;
        this.userPasswd = userPasswd;
        this.role = role;
    }
    //생성자 호출시 자동 호출 됌
    private void setRole() {
        switch (this.gradeCode.intValue()) {
            case 1:
            case 2:
            case 3:
                this.role = Role.USER;
                break;
            case 4:
                this.role = Role.ADMIN;
                break;
            default:
                this.role = Role.USER; // 기본 역할 설정
        }
    }

    /**
     *  이 밑의 메서드들은 UserDetails의 구현체들
     *
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getKey()));
    }

    @Override
    public String getPassword() {
        return userPasswd;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public UserInfoBySession getUserInfoBySession() {
        return this;
    }
}
