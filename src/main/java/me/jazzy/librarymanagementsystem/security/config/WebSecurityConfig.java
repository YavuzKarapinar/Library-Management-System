package me.jazzy.librarymanagementsystem.security.config;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.security.jwt.JWTAuthenticationFilter;
import me.jazzy.librarymanagementsystem.security.jwt.JwtGenerator;
import me.jazzy.librarymanagementsystem.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserService userService;
    private final JwtGenerator jwtGenerator;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests(configurer -> {
                    configurer.requestMatchers(HttpMethod.POST, "/api/v*/auth/login")
                            .permitAll(); // Login yolu için izin veriyoruz
                    configurer.requestMatchers(HttpMethod.POST, "/api/v*/auth/register")
                            .permitAll(); // Register yolu için izin veriyoruz
                    configurer.requestMatchers(HttpMethod.POST, "/api/v*/**")
                            .hasAuthority("MANAGER");
                    configurer.requestMatchers(HttpMethod.GET, "/api/v*/**")
                            .permitAll();
                    configurer.requestMatchers(HttpMethod.PUT, "/api/v*/**")
                            .hasAnyAuthority("STAFF", "MANAGER");
                    configurer.requestMatchers("/api/v*/auth/**")
                            .permitAll();
                    configurer.anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JWTAuthenticationFilter authenticationFilter() {
        return new JWTAuthenticationFilter(jwtGenerator, userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
