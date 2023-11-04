package fr.sncf.d2d.up2dev.tortycolis.users.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import fr.sncf.d2d.up2dev.tortycolis.users.models.User;


@Configuration
@ConfigurationProperties(prefix = "tortycolis")
public class UsersConfigurationProperties {
    
    private final List<User> users;

    /** Le autowired est ici nécessaire pour indiquer à Spring que le constructeur est à utiliser pour l'injection via @ConfigurationProperties */
    @Autowired
    public UsersConfigurationProperties(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers(){
        return this.users;
    }
}
