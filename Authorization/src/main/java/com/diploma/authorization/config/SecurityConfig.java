package com.diploma.authorization.config;

import com.diploma.authorization.model.User;
import com.diploma.authorization.service.UserDetailsServiceImpl;
import com.diploma.authorization.util.JwtAuthenticationEntryPoint;
import com.diploma.authorization.util.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
//import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
//import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//@EnableJdbcHttpSession
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    @Qualifier(value = "UserDetailsServiceImpl")
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtFilter filter;

    public SecurityConfig(JwtAuthenticationEntryPoint authenticationEntryPoint, UserDetailsServiceImpl userDetailsService, JwtFilter filter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailsService = userDetailsService;
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
    public AuthenticationManager authenticationManagerBean() throws
            Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .logout().logoutSuccessUrl("/logout")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
//        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
//    }
//
//    @Bean
//    public static SessionRegistry sessionRegistry(JdbcIndexedSessionRepository sessionRepository) {
//        return new SpringSessionBackedSessionRegistry(sessionRepository);
//    }

}

// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// public class SecurityConfig extends WebSecurityConfigurerAdapter{

// private final UserDetailsServiceImpl userDetailsServiceImpl;

// @Autowired
// public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
// this.userDetailsServiceImpl = userDetailsServiceImpl;
// }

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http
// .cors().and()
// .csrf().disable()
// .authorizeRequests()
// .antMatchers("/admin").hasRole("ADMIN")
// .antMatchers("/docs/**").hasRole("USER")
// .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
// .anyRequest().permitAll()
// // .hasAnyRole("USER", "ADMIN")
// .and()
// .formLogin().loginPage("/auth/login")
// .loginProcessingUrl("/process_login")
// .defaultSuccessUrl("/docs/dto", true)
// .failureUrl("/auth/login?error")
// .and()
// .logout()
// .logoutUrl("/logout")
// .logoutSuccessUrl("/auth/login")
// .and()
// .logout()
// .deleteCookies("JSESSIONID")
// .and()
// .rememberMe()
// .key("uniqueAndSecret")
// .tokenValiditySeconds(3600);
// }

// // Наѝтраиваем аутентификацию
// @Override
// protected void configure(AuthenticationManagerBuilder auth) throws Exception
// {
// auth.userDetailsService(userDetailsServiceImpl)
// .passwordEncoder(getPasswordEncoder());
// }

// @Bean
// public PasswordEncoder getPasswordEncoder() {
// return new BCryptPasswordEncoder();
// }

// }