package org.projekat.security;

import org.projekat.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth->auth //TODO(en):testirati oce li pulat rektora i ostale kad otvorim ovo
                        .requestMatchers("/auth/v1/login").permitAll()
                        .requestMatchers("/auth/v1/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/auth/v1/user/**").hasAnyAuthority("ROLE_USER")
                        .requestMatchers("/auth/v1/nastavnik/**").hasAnyAuthority("ROLE_NASTAVNIK")
                            .requestMatchers("/auth/v1/student/**").hasAnyAuthority("ROLE_STUDENT")
                            .requestMatchers("/auth/v1/osoba/**").hasAnyAuthority("ROLE_STUDENT","ROLE_ADMIN","ROLE_NASTAVNIK")
                        .anyRequest()
                        .authenticated()
                ).sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
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
