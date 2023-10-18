package fr.sncf.d2d.up2dev.tortycolis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class TortycolisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TortycolisApplication.class, args);
	}

}
