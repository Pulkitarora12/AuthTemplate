package com.secure.notes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                (request) -> request
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/admin").denyAll()
                        .anyRequest().authenticated());

        http.csrf(csrf -> csrf.disable());

        // in stateless authentication, no data is stored inside sessions, means we need to send credentials everytime
        // and it only works with basic, because form is designed to store the sessions
        // so in stateless, the credentials are stoored and sent by the browser in every request
//        http.sessionManagement(
//                (session) ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // this store data in the form of sessions
        // in statefull the session is sent
//        http.formLogin(withDefaults());  // has login/logout pages
        http.httpBasic(withDefaults());  // doesn't have any pages

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        if (!manager.userExists("admin")) {
            manager.createUser(
                    User.withUsername("admin")
                            .password("{noop}adminPassword")
                            .roles("ADMIN")
                            .build()
            );
        }
        if (!manager.userExists("user")) {
            manager.createUser(
                    User.withUsername("user")
                            .password("{noop}userPassword")
                            .roles("USER")
                            .build()
            );
        }

        return manager;
    }
}
