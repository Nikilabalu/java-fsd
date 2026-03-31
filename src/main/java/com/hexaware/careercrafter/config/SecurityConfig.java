package com.hexaware.careercrafter.config;

import com.hexaware.careercrafter.filter.JwtFilter;
import com.hexaware.careercrafter.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())

                .authorizeHttpRequests(authorize -> authorize

                        // Public APIs
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/user/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/auth/login").permitAll()

                        // Public Job APIs
                        .requestMatchers(HttpMethod.GET, "/api/job/get-all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/job/get/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/job/job-types").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/job/experience-levels").permitAll()

                        // Role-based APIs
                        .requestMatchers(HttpMethod.POST, "/api/job/apply/**")
                        .hasAuthority("JOB_SEEKER")

                        .requestMatchers(HttpMethod.POST, "/api/job/create")
                        .hasAuthority("COMPANY")

                        .requestMatchers(HttpMethod.PUT, "/api/job/update/**")
                        .hasAuthority("COMPANY")

                        .requestMatchers(HttpMethod.DELETE, "/api/job/delete/**")
                        .hasAuthority("COMPANY")

                        .requestMatchers("/api/admin/**")
                        .hasAuthority("ADMIN")

                        // Everything else secured
                        .anyRequest().authenticated()
                );

        // JWT filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        //  Basic Auth
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }



}