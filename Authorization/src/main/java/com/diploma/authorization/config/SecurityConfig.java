package com.diploma.authorization.config;

import com.diploma.authorization.service.UserDetailsServiceImpl;
import com.diploma.authorization.util.JwtAuthenticationEntryPoint;
import com.diploma.authorization.util.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtFilter filter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint authenticationEntryPoint, JwtFilter filter) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.filter = filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
//                .and()
//                .sessionManagement()
//                //Указываем макимальное возможное количество сессий(тут указано не 1, т.к. мы будем пользоваться своей кастомной стратегией, объяснение будет ниже)
//                .maximumSessions(3)
//                //При превышение количества активных сессий(3) выбрасывается исключение  SessionAuthenticationException
//                .maxSessionsPreventsLogin(true)
//                //Указываем как будут регестрироваться наши сессии(тогда во всем приложение будем использовать именно этот бин)
//                .sessionRegistry(sessionRegistry).and()
//                //Добавляем нашу кастомную стратегию для проверки кличества сессий
//                .sessionAuthenticationStrategy(concurrentSessionStrategy)
//                //Добавляем перехватчик для исключений
//                .sessionAuthenticationFailureHandler(securityErrorHandler);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new     UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
////        config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:4200"));
////        config.setAllowedOrigins(Collections.singletonList("*"))
//        config.setAllowCredentials(false);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PATCH");
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }



}
