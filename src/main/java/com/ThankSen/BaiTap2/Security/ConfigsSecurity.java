package com.ThankSen.BaiTap2.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@EnableWebSecurity
public class ConfigsSecurity {
    private JwtFilter jwtFilter;

    @Autowired
    public ConfigsSecurity(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf((httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable()))
            .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable())
            .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers("/user/**", "/file/**","/").permitAll()
                            .requestMatchers("/role/**").authenticated()
            ).addFilterBefore(jwtFilter , AuthorizationFilter.class);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

}
