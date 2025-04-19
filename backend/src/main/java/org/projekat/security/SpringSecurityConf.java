package org.projekat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConf {
     private UserDetailsService userDetailsService;
     private JwtAuthFilter authFilter;
     private JwtAuthEntryPoint entryPoint;

    public SpringSecurityConf(UserDetailsService userDetailsService, JwtAuthFilter authFilter, JwtAuthEntryPoint entryPoint) {
        this.userDetailsService = userDetailsService;
        this.authFilter = authFilter;
        this.entryPoint = entryPoint;
    }
    public SpringSecurityConf(){}

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // !!!https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html!!! kako se dodava novi
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable()).authorizeRequests((authorize)->{
            //authorize.requestMatchers(new AntPathRequestMatcher("/user/**")).hasAuthority("User").anyRequest().authenticated();// Primjer  !!!TODO(en):CVE na requestMachers
            authorize.requestMatchers(new AntPathRequestMatcher("/api/auth/**")).permitAll();
            authorize.anyRequest().authenticated();//ovo je za sve koji ostalo
        }).httpBasic(Customizer.withDefaults());
        http.exceptionHandling(exception->exception.authenticationEntryPoint(entryPoint));
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception{
        return conf.getAuthenticationManager();
    }

}
