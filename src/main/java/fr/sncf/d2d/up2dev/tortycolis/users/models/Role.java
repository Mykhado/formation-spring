package fr.sncf.d2d.up2dev.tortycolis.users.models;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMINISTRATOR,
    DELIVERY_PERSON;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }

    public static Set<Role> fromAuthorities(Collection<? extends GrantedAuthority> authorities){
        return authorities.stream()
            .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
            .map(roleString -> Role.valueOf(roleString.getAuthority().substring("ROLE_".length())))
            .collect(Collectors.toUnmodifiableSet());
    }
}
