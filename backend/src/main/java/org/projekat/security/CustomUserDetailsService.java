package org.projekat.security;

import org.projekat.model.User;
import org.projekat.repositorys.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(emailOrUsername,emailOrUsername)
                .orElseThrow(()->new UsernameNotFoundException("Nema korisnika po tom usernameu ili email"));
        Set<GrantedAuthority>authorities = user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(emailOrUsername,user.getPassword(),authorities);
    }
}
