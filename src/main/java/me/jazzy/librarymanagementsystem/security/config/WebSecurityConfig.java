package me.jazzy.librarymanagementsystem.security.config;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.security.PasswordEncoder;
import me.jazzy.librarymanagementsystem.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests(configurer -> {
                    configurer.requestMatchers(HttpMethod.POST, "/api/v*/books")
                            .hasAuthority("MANAGER");
                    configurer.requestMatchers("/api/v*/registration")
                            .permitAll();
                    configurer.anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder.encoder());
        provider.setUserDetailsService(userService);
        return provider;
    }
}
