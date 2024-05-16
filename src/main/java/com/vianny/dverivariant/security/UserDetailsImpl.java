package com.vianny.dverivariant.security;

import com.vianny.dverivariant.models.Accounts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String login;
    private String password;

    public UserDetailsImpl(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public static UserDetails build(Accounts accounts) {
        return new UserDetailsImpl(
                accounts.getId(),
                accounts.getLogin(),
                accounts.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
}
