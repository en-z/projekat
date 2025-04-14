package org.projekat.security;

import org.projekat.model.User;
import org.projekat.repositorys.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(emailOrUsername,emailOrUsername).orElseThrow(()->new RuntimeException("user not found"));
        return new org.springframework.security.core.userdetails.User(emailOrUsername,user.getPassword(),null); //TODO(en):Dodaj authorities i dodati rename user od springframeworka
    }
}
