package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
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
         * Load category/product data **
         */


        Category tempcategory = new Category("Books");
        categoryRepository.save(tempcategory);

        Product tempproduct = new Product("Learn HTML5", "Learn the fundamentals of HTML5 within 24 hours", 25.67, 10);
        tempproduct.setCategory(tempcategory);
        tempproduct.setImg ("https://images-na.ssl-images-amazon.com/images/I/514axA2lwpL.jpg");
        productRepository.save (tempproduct);

        tempproduct = new Product("Java Fundamentals", "Learn the fundamentals of Java programming within 24 hours",20.99, 6);
        tempproduct.setCategory (tempcategory);
        tempproduct.setImg ("https://images-na.ssl-images-amazon.com/images/I/514axA2lwpL.jpg");
        productRepository.save(tempproduct);


    }
}
