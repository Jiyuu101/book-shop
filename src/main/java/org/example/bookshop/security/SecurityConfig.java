package org.example.bookshop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
        throws Exception{
        return config.getAuthenticationManager();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider =
//                new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(userDetailsService);
//        return provider;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
            Exception {
        http.formLogin(c -> {
            c.loginPage("/auth/login").permitAll();
            c.defaultSuccessUrl("/");
        });
        http.logout(c -> {
            c.logoutUrl("/logout").permitAll();
            c.logoutSuccessUrl("/");
        });
        System.out.println("login security config....");
        http.csrf(c -> c.disable());
        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/","/home/**","/bootstrap/**","/images/**","/auth/register","/auth/checkout",
                    "/auth/login").permitAll();
            c.requestMatchers("/admin/**").hasRole("ADMIN");
            c.anyRequest().authenticated();
        });
        return http.build();
    }
}
