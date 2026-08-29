package com.mohit.shoppingnotification.config;

import com.mohit.shoppingnotification.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .authorizeHttpRequests(auth -> auth

                        // Authentication endpoints
                        .requestMatchers(
                                "/auth/register",
                                "/auth/login"
                        ).permitAll()

                        // Anyone authenticated can READ products
                        .requestMatchers(HttpMethod.GET, "/products/**")
                        .hasAnyRole("USER", "ADMIN")

                        // Only ADMIN can CREATE products
                        .requestMatchers(HttpMethod.POST, "/products/**")
                        .hasRole("ADMIN")

                        // Only ADMIN can UPDATE products
                        .requestMatchers(HttpMethod.PUT, "/products/**")
                        .hasRole("ADMIN")

                        // Only ADMIN can DELETE products
                        .requestMatchers(HttpMethod.DELETE, "/products/**")
                        .hasRole("ADMIN")

                        // Everything else requires authentication
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}