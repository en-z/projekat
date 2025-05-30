package org.projekat.service.users;

import org.projekat.jwt.CustomUserDetails;
import org.projekat.model.User;
import org.projekat.repository.AdresaRepository;
import org.projekat.repository.users.OsobaRepository;
import org.projekat.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdresaRepository adresaRepository;
    @Autowired
    private OsobaRepository osobaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetails= userRepository.findByEmail(username);
        return userDetails.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found "+username));
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
