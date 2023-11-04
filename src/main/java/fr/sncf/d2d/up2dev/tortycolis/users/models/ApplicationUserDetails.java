package fr.sncf.d2d.up2dev.tortycolis.users.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Une implémentation de {@link UserDetails} prévue pour encapsuler un objet {@link User} applicatif. 
 */
public class ApplicationUserDetails implements UserDetails {

    private final User user;

    public ApplicationUserDetails(User user){
        this.user = user;
    }

    public User getDomainUser(){
        return this.user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(user.getRole());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
