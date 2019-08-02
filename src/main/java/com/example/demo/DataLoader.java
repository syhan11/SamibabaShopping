package com.example.demo;

import org.hibernate.criterion.Order;
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

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

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

        User tuser = new User("sueyoung.6311@gmail.com", "password", "Sue",
                "Han", true, "sue");
        tuser.setRoles(Arrays.asList(userRole));
        userRepository.save(tuser);

        user = new User("admin@admin.com", "password", "Admin",
                "User", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        /*
         * Load category/product data **
         */


//        Category tempcategory = new Category("Books");
//        categoryRepository.save(tempcategory);
        Category tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1564604637/codyxktifpfte3f6sbrq.jpg");
        tempcategory.setTitle("Books");
        categoryRepository.save(tempcategory);

        // products for Books categroy
        Product tempproduct = new Product("Learn HTML5", "Learn the fundamentals of HTML5 within 24 hours", 25.67, 10);
        tempproduct.setCategory(tempcategory);
        tempproduct.setImg ("https://images-na.ssl-images-amazon.com/images/I/514axA2lwpL.jpg");
        productRepository.save (tempproduct);

        Product tempproduct2 = new Product("Java Fundamentals", "Learn the fundamentals of Java programming within 24 hours",20.99, 6);
        tempproduct2.setCategory (tempcategory);
        tempproduct2.setImg ("https://images-na.ssl-images-amazon.com/images/I/514axA2lwpL.jpg");
        productRepository.save(tempproduct2);


        // new category
        tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1564598654/kvsr95fpwgemhnlm2tg0.jpg");
        tempcategory.setTitle("Clothing");
        categoryRepository.save(tempcategory);

        tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1564767781/images_kidqfz.jpg");
        tempcategory.setTitle("Home Appliances ");
        categoryRepository.save(tempcategory);

        tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1564671180/zeswg50joazsfqvpz8ul.jpg");
        tempcategory.setTitle("Electronic Devices");
        categoryRepository.save(tempcategory);



        // create test order history: open status for user 3
        OrderHistory tmp = new OrderHistory();
        tmp.setOrderId("testing001");
        tmp.setOrdproduct(tempproduct);
        tmp.setQty(99);
        tmp.setStatus(2);
        tmp.setOrduser(tuser);
        orderHistoryRepository.save(tmp);

        // create test order history: non-open status for user 3
        tmp = new OrderHistory();
        tmp.setOrderId("testing002");
        tmp.setOrdproduct(tempproduct2);
        tmp.setQty(11);
        tmp.setStatus(3);
        tmp.setOrduser(tuser);
        orderHistoryRepository.save(tmp);

        // create test order history: open status for user 4
        tmp = new OrderHistory();
        tmp.setOrderId("testing003");
        tmp.setOrdproduct(tempproduct2);
        tmp.setQty(1);
        tmp.setStatus(3);
        tmp.setOrduser(user);
        orderHistoryRepository.save(tmp);


    }
}
