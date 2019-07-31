package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... strings) throws Exception {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("dtmogh@gmail.com", "password", "David",
                     "Mojh", true, "david");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("jacoblepler91@gmail.com", "password", "Jacob",
                "Lepler", true, "jacob");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("sueyoung.6311@gmail.com", "password", "Sue",
                "Han", true, "sue");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin",
                "User", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        /*
         * initialze category table
         */
        Category category = new Category("Books", "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1564595589/shutterstock_1068141515.jpg");
        categoryRepository.save(category);



    }
}
