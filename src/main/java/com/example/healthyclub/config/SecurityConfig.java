package com.example.healthyclub.config;

import com.example.healthyclub.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public BCryptPasswordEncoder encoder(){return new BCryptPasswordEncoder(); }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //http 시큐리티 빌더
        http.cors()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/friend/*","/auth/**","/auth/login","/auth/join","/routine/*").permitAll()
                .anyRequest().authenticated();

        //토큰 인증 필터 등록
        http.addFilterAfter( //어떤 필터 뒤에 추가한다
                jwtAuthFilter,
                CorsFilter.class //import 주의! 스프링 프레임워크인 애를 import 해야한다
        );
        return http.build();
    }
}