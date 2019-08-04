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


        // new category-clothing
        tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1564598654/kvsr95fpwgemhnlm2tg0.jpg");
        tempcategory.setTitle("Clothing");
        categoryRepository.save(tempcategory);

        tempproduct2 = new Product("Black Faux Leather Jacket",
                "Robert Downey Jr Iron Man Tony Stark Superhero Stylish Black Faux Leather Jacket",
                79.99, 5);
        tempproduct2.setCategory (tempcategory);
        tempproduct2.setImg ("https://m.media-amazon.com/images/I/51kYT4gQmEL._AC_UL480_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct2);

        tempproduct2 = new Product("Rain Jacket",
                "Marmot PreCip Men's Lightweight Waterproof Rain Jacket",
                72.99, 3);
        tempproduct2.setCategory (tempcategory);
        tempproduct2.setImg ("https://images-na.ssl-images-amazon.com/images/I/91ek1q7A7gL._UY879_.jpg");
        productRepository.save(tempproduct2);

        tempproduct2 = new Product("MIARHB Casual Hoodie",
                "MIARHB Casual Hoodie for Men Zip-up Long Sleeve Hooded Jacket Slim Fit Coat with Pockets",
                13.99, 9);
        tempproduct2.setCategory (tempcategory);
        tempproduct2.setImg ("https://images-na.ssl-images-amazon.com/images/I/71x6Vnda31L._UX522_.jpg");
        productRepository.save(tempproduct2);

        // new category - home appliances
        tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1564767781/images_kidqfz.jpg");
        tempcategory.setTitle("Home Appliances ");
        categoryRepository.save(tempcategory);


        tempproduct2 = new Product("Home/Office PowerPort strip with long 6ft Cord for Smartphone Tablets Home Appliances",
                "UL listed #14 power cord（E312632）assure your family safety. Built-in over-current, over-voltage, over-heat, short-circuit protection; independent safe guard provides premium protection for both home and professional workstations.",
                15.99, 7);
        tempproduct2.setCategory (tempcategory);
        tempproduct2.setImg ("https://m.media-amazon.com/images/I/51imDGx2UwL._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct2);

        tempproduct2 = new Product("Cuisinart ICE-21 Frozen Yogurt-Ice Cream Maker",
                "New mixing paddle makes frozen desserts in 20 minutes or less\n" +
                        "Large capacity makes up to 1-1/2-quarts\n" +
                        "Double insulated freezer bowl eliminates the need for ice",
                42.99, 3);
        tempproduct2.setCategory (tempcategory);
        tempproduct2.setImg ("https://m.media-amazon.com/images/I/61NZiHE74cL._AC_UL480_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct2);

        tempproduct2 = new Product("AmazonBasics 8-Sheet Strip-Cut Paper, CD and Credit Card Home Office ",
                "Strip-cut paper shredder with 8-sheet capacity (no need to remove staples); destroys credit cards, CDs, and DVDs (one at a time)",
                34.99, 3);
        tempproduct2.setCategory (tempcategory);
        tempproduct2.setImg ("https://m.media-amazon.com/images/I/81HM1ZlI23L._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct2);

        tempproduct2 = new Product("MEIYIN New Mini Portable USB Charging Fan Rotating Silent Clip Fan Cooler",
                "Made of high-quality material, durable, firm and nontoxic for us to use.\n" +
                        "With clip design, convenient and easy for us to use, and a wider range of uses.",
                19.99, 15);
        tempproduct2.setCategory (tempcategory);
        tempproduct2.setImg ("https://m.media-amazon.com/images/I/614sCKxSIqL._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct2);


        // new category - electronic devices
        tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1564671180/zeswg50joazsfqvpz8ul.jpg");
        tempcategory.setTitle("Electronic Devices");
        categoryRepository.save(tempcategory);

        Product tempproduct3 = new Product("Mini Projector",
                "Supported resolution:1920*1080 with projector distance 1.5m-5m.",
                99.99, 7);
        tempproduct3.setCategory (tempcategory);
        tempproduct3.setImg ("https://m.media-amazon.com/images/I/71xyQM1Lj8L._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct3);


        // create test order history: open status for user 3
        OrderHistory tmp = new OrderHistory();
        tmp.setOrderId("testing001");
        tmp.setOrdproduct(tempproduct);
        tmp.setQty(1);
        tmp.setStatus(2);
        tmp.setOrduser(tuser);
        orderHistoryRepository.save(tmp);

        // create test order history: non-open status for user 3
        tmp = new OrderHistory();
        tmp.setOrderId("testing002");
        tmp.setOrdproduct(tempproduct2);
        tmp.setQty(4);
        tmp.setStatus(3);
        tmp.setOrduser(tuser);
        orderHistoryRepository.save(tmp);

        // create test order history: open status for user 4
        tmp = new OrderHistory();
        tmp.setOrderId("testing003");
        tmp.setOrdproduct(tempproduct2);
        tmp.setQty(2);
        tmp.setStatus(3);
        tmp.setOrduser(user);
        orderHistoryRepository.save(tmp);


    }
}
