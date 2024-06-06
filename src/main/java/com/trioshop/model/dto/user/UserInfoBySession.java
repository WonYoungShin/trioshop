package com.trioshop.model.dto.user;

import com.trioshop.utils.service.Role;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Getter
@ToString
public class UserInfoBySession implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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
