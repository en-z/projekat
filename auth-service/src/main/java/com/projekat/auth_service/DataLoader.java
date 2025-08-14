package com.projekat.auth_service;

import com.projekat.auth_service.entity.User;
import com.projekat.auth_service.repository.UserRepository;
import com.projekat.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UserService userRepository;
    @Override
    public void run(String... args) throws Exception {

        User u = new User();
        u.setEmail("admin@test.com");
        u.setPassword("admin");
        List<String> list= new ArrayList<>();
        list.add("ROLE_ADMIN");
        u.setRoles(list);
        userRepository.save(u);
        User i = new User();
        i.setEmail("nastavnik@test.com");
        i.setPassword("test123");
        List<String> list1= new ArrayList<>();
        list1.add("ROLE_NASTAVNIK");
        i.setRoles(list1);
        userRepository.save(i);
        User r = new User();
        r.setEmail("student@test.com");
        r.setPassword("test123");

        List<String> list2= new ArrayList<>();
        list2.add("ROLE_STUDENT");
        r.setRoles(list2);
        userRepository.save(r);
        System.out.println("DOne");

    }
}
