package com.eklauth.microserviceAuth.security;
import com.eklauth.microserviceAuth.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author isaac
 */
public class UserPrincipal implements UserDetails{

    private Collection<? extends GrantedAuthority> authorities;
    private final User user;

    public UserPrincipal(Collection<? extends GrantedAuthority> authorities, User user) {
        this.authorities = authorities;
        this.user = user;
    }

    public static UserPrincipal create(User user){
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new UserPrincipal(authorities, user);
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
       return user.getPassword();
    }

    @Override
    public String getUsername() {
       return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getStatus()==true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus()==true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
         return user.getStatus()==true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus()==true;
    }

    public User getUser() {   //GET USER SET OBJECT
        return user;
    }
    
    
}
