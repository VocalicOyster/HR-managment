package it.portfolio.hr.humanResource.models;

import it.portfolio.hr.humanResource.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;
    private final String companyName;


    public UserDetailsImpl(String username, String password, String companyName) {
        this.username = username;
        this.password = password;
        this.companyName = companyName;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public static UserDetailsImpl build(User user) {

        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                user.getCompanyName()
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
