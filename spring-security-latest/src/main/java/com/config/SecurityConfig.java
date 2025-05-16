 package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.filter.JwtAuthFilter;

import jakarta.ws.rs.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    //authentication
    @Bean
    UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                
                // ADMIN access
                .requestMatchers("/users/**", "/hotel/**", "/rooms/**", "/booking/**", "/payment/**", "/review/**")
                    .hasRole("ADMIN")

                // USER access (GET only for hotel, rooms, review; POST/GET for booking; POST/DELETE for payment)
                .requestMatchers(HttpMethod.GET, "/hotel/**", "/rooms/**", "/review/**").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/booking/**", "/payment/**","/review/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/booking/**").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/payment/**").hasRole("USER")

                // MANAGER access
                .requestMatchers(HttpMethod.GET, "/hotel/**", "/rooms/**", "/booking/**", "/payment/**", "/review/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/hotel/**", "/rooms/**", "/review/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/rooms/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/rooms/**", "/review/**").hasRole("MANAGER")

                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
