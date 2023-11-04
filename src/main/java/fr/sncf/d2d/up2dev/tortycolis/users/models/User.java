package fr.sncf.d2d.up2dev.tortycolis.users.models;

import java.util.UUID;

/**
 * Un utilisateur de l'application (livreur ou administrateur).
 */
public class User {

    private final UUID id;

    private final String email;

    private final String password;

    private final Role role;

    public User(UUID id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UUID getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }
}
