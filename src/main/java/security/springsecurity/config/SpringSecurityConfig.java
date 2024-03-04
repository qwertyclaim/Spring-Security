package security.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import security.springsecurity.services.PersonDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private final PersonDetailsService service;

    @Autowired
    public SpringSecurityConfig(PersonDetailsService service) {
        this.service = service;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/auth/authorize", "/auth/login", "/from").permitAll()
                        .anyRequest().hasAnyRole("USER", "ADMIN"))
                .formLogin((form) -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/hello")
                        .failureUrl("/auth/login?error"))
                .userDetailsService(service)
                .httpBasic(Customizer.withDefaults())
                .logout((log) -> log
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login"));
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}