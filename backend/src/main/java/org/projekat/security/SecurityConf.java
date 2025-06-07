package org.projekat.security;

import jakarta.annotation.PostConstruct;
import org.projekat.jwt.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@EnableWebSecurity
public class SecurityConf {
    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;
    public SecurityConf(JwtAuthFilter jwtAuthFilter, UserDetailsService userDetailsService, PasswordEncoderConf pwConf){
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/login", "/api/users/registration","/error").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/univerzitet/**","/api/fakultet/**","/api/program/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/admin/**","/api/univerzitet/**","/api/fakultet/**","/api/program/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/admin/**","/api/univerzitet/**","/api/fakultet/**","/api/program/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/univerzitet/**","/api/fakultet/**","/api/program/**").hasAnyAuthority("ROLE_ADMIN")
                        // .requestMatchers("/api/nastavnici/**").hasRole("NASTAVNIK")
                        .anyRequest().permitAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
       return config.getAuthenticationManager();
    }

}
