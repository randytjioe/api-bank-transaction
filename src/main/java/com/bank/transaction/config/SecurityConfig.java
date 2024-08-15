package com.bank.transaction.config;

import com.bank.transaction.security.CustomAuthenticationEntryPoint;
import com.bank.transaction.security.JwtAuthenticationFilter;
import com.bank.transaction.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final UserSecurity userSecurity;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        // Auth endpoints
                        .requestMatchers("/api/auth/**").permitAll()

                        // Rekening endpoints
                        .requestMatchers(HttpMethod.POST, "/api/rekening").authenticated()
                        .requestMatchers(HttpMethod.GET,  "/api/rekening/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/rekening/*").authenticated()

                        // Transaksi endpoints
                        .requestMatchers(HttpMethod.POST, "/api/transaksi").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/transaksi", "/api/transaksi/*").authenticated()

                        // Nasabah endpoints
                        .requestMatchers("/api/nasabah").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/nasabah/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/nasabah/*").authenticated()


                        // Any other request
                        .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}