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
    
    private List<User> users;

    @Autowired
    public UsersConfigurationProperties(List<User> users) {
        System.out.println(users);
        this.users = users;
    }

    public List<User> getUsers(){
        return this.users;
    }
}
