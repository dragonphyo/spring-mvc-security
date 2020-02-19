package com.example.springmvcsecurity.security;

import com.example.springmvcsecurity.domain.Roles;
import com.example.springmvcsecurity.domain.User;
import com.example.springmvcsecurity.repository.RolesRepository;
import com.example.springmvcsecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DBLoader implements CommandLineRunner {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    public DBLoader(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, RolesRepository rolesRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Roles admin = new Roles();
        admin.setName("ROLE_ADMIN");

        Roles user = new Roles();
        user.setName("ROLE_USER");

        User adminUser = new User();
        adminUser.setEmail("kyaw@gmail.com");
        adminUser.setPassword(bCryptPasswordEncoder.encode("kyaw"));

        User userUser = new User();
        userUser.setEmail("thaw@gmail.com");
        userUser.setPassword(bCryptPasswordEncoder.encode("thaw"));

        adminUser.getRoles().add(admin);
        adminUser.getRoles().add(user);

        userUser.getRoles().add(user);

//        userRepository.save(userUser);
//        userRepository.save(adminUser);


    }
}
