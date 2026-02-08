package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;
import lombok.extern.slf4j.Slf4j;

@Slf4j   // ✅ CORRECT
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 🔥 LOG #1 — SecurityConfig is loaded
        log.info("✅ SecurityConfig loaded - securityFilterChain started");

        http
            .csrf(csrf -> {
                log.info("❌ CSRF disabled");
                csrf.disable();
            })

            .formLogin(form -> {
                log.info("❌ Form Login disabled");
                form.disable();
            })

            .httpBasic(basic -> {
                log.info("❌ HTTP Basic disabled");
                basic.disable();
            })

            .authorizeHttpRequests(auth -> {
                log.info("🔐 Authorization rules configured");

                auth
                    .requestMatchers("/users/login", "/users/signup", "/test", "/refresh").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                    .anyRequest().authenticated();
            })

            .sessionManagement(session -> {
                log.info("📦 Session policy set to STATELESS");
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })

            .addFilterBefore((Filter) jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // 🔥 LOG #2 — JWT filter added
        log.info("🛡️ JwtAuthFilter added before UsernamePasswordAuthenticationFilter");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("🔑 BCryptPasswordEncoder bean created");
        return new BCryptPasswordEncoder();
    }
}
