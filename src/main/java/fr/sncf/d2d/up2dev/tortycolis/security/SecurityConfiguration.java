package fr.sncf.d2d.up2dev.tortycolis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import fr.sncf.d2d.up2dev.tortycolis.users.persistence.UsersRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    
    private final UsersRepository usersRepository;

    public SecurityConfiguration(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    /**
     * Ici, on configure la chaîne de filtres de Spring Security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            /**
             * Il existe de nombreux modes d'authentification supportés par Spring Security.
             * Ici on met en place une simple authentification HTTP Basic (https://fr.wikipedia.org/wiki/Authentification_HTTP).
             */
            .httpBasic(Customizer.withDefaults())
            /**
             * Pour que Spring Security puisse lire notre base d'utilisateurs et leur mots de passe lors de l'authentification, on fournit
             * un bean de type UserDetailsService.
             */
            .userDetailsService(this.usersRepository)
            /**
             * Les attaques CSRF sont très répandues dans le web, on peut se référer à cette documentation pour savoir comment la configurer
             * lorsqu'on utilise Spring en tant qu'API Rest avec comme client une Single Page Application (une application Angular par ex):
             * https://dev.sncf/docs/security/csrf#les-syst%C3%A8mes-de-protection-par-jeton
             * Ici, la configuration est volontairement désactivée pour simplifier la formation mais cela peut être problématique en production.
             */
            .csrf(csrf -> csrf.disable())
            /**
             * Cette configuration est uniquement une première couche de contrôle d'accès. Elle termine par authorize.anyRequest().permitAll().
             * Chaque fonctionnalité doit implémenter son propre contrôle d'accès (en mode liste blanche).
             * C'est ce qui est fait via les annotations de type @PreAuthorize/@PostAuthorize.
             * 
             * Dans certaines applications, on peut se permettre de configurer intégralement le contrôle via ce type de configuration.
             * Il faudra alors se baser sur un modèle terminant par authorize.anyRequest().denyAll() (liste blanche).
             */
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/packages").permitAll()
                .requestMatchers(HttpMethod.POST, "/packages").hasAnyRole("ADMINISTRATOR", "DELIVERY_PERSON")
                .anyRequest().permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
