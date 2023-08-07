package com.example.jubileebudgetapp.config;

import com.example.jubileebudgetapp.filter.JwtRequestFilter;
import com.example.jubileebudgetapp.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // authentication
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    // authorization with jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception{

        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests()

                // user
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/users/{username}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/users/{username}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PATCH, "/users/{username}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.DELETE, "/users/{username}").hasRole("ADMIN")

                // account
                .requestMatchers(HttpMethod.POST, "/accounts").permitAll()
                .requestMatchers(HttpMethod.PUT, "/accounts/{username}/{accountId}").hasAnyRole("ADMIN", "USER")

                .requestMatchers(HttpMethod.GET, "/accounts").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/accounts/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/accounts/{id}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PATCH, "/accounts/{id}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.DELETE, "/accounts/{id}").hasRole("ADMIN")

                // balance
                .requestMatchers(HttpMethod.GET, "/balances/{accountId}").hasAnyRole("ADMIN", "USER")

                // contract
                .requestMatchers(HttpMethod.POST, "/contracts").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/contracts/{id}/{accountId}").hasAnyRole("ADMIN", "USER")

                .requestMatchers(HttpMethod.GET, "/contracts").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/contracts/{id}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT, "/contracts/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/contracts/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/contracts/{id}").hasAnyRole("ADMIN", "USER")

                // saving goal
                .requestMatchers(HttpMethod.POST, "/saving_goals").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/saving_goals/{id}/{accountId}").hasAnyRole("ADMIN", "USER")

                .requestMatchers(HttpMethod.GET, "/saving_goals").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/saving_goals/{id}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT, "/saving_goals/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/saving_goals/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/saving_goals/{id}").hasAnyRole("ADMIN", "USER")

                // transaction
                .requestMatchers(HttpMethod.POST, "/transactions").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/transactions/{id}/{accountId}").hasAnyRole("ADMIN", "USER")

                .requestMatchers(HttpMethod.GET, "/transactions").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/transactions/{id}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT, "/transactions/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/transactions/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/transactions/{id}").hasAnyRole("ADMIN", "USER")

                // file
                .requestMatchers(HttpMethod.POST, "/files/single/upload").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/files/{id}/{accountId}").hasAnyRole("ADMIN", "USER")

                .requestMatchers(HttpMethod.GET, "/files/download/{fileId}").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/files/{fileId}").hasRole("USER")

                // authentication
                .requestMatchers("/authenticated").authenticated()
                .requestMatchers("/authenticate").permitAll()

                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
