package com.example.demo;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    // cancel=1; standby=2; ordered=3; shipped=4; wish = 5; cancelAdmin=6
    static int ORDCANCEL = 1;
    static int ORDSTANDBY = 2;
    static int ORDORDERED = 3;
    static int ORDSHIPPED = 4;
    static int ORDWISH = 5;
    static int ORDADMCANCEL = 6;

    @Override
    public void run(String... strings) throws Exception {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user1 = new User("dtmogh@gmail.com", "password", "David",
                     "Mojh", true, "david");
        user1.setRoles(Arrays.asList(userRole));
        userRepository.save(user1);

        User user2 = new User("jacoblepler91@gmail.com", "password", "Jacob",
                "Lepler", true, "jacob");
        user2.setRoles(Arrays.asList(userRole));
        userRepository.save(user2);

        User user3 = new User("sueyoung.6311@gmail.com", "password", "Sue",
                "Han", true, "sue");
        user3.setRoles(Arrays.asList(userRole));
        userRepository.save(user3);

        User adminuser = new User("admin@admin.com", "password", "Admin",
                "User", true, "admin");
        adminuser.setRoles(Arrays.asList(adminRole));
        userRepository.save(adminuser);

        /*
         * Load category/product data **
         */


//        Category tempcategory = new Category("Books");
//        categoryRepository.save(tempcategory);
        Category tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1565027620/79578b6a0b2c5bc_qwrvjt.jpg");
        tempcategory.setTitle("Books");
        categoryRepository.save(tempcategory);

        // products for Books categroy
        long catid = tempcategory.getId();
        Product tempproduct1 = new Product("HTML and CSS: Design and Build Websites", "Learn the fundamentals of HTML5 within 24 hours", 25.67, 10);
        tempproduct1.setCategory(tempcategory);
        tempproduct1.setImg ("https://m.media-amazon.com/images/I/41p7u2kJACL._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save (tempproduct1);

        tempproduct1 = new Product("Java Fundamentals", "Learn the fundamentals of Java programming within 24 hours",20.99, 6);
        tempproduct1.setCategory (tempcategory);
        tempproduct1.setImg ("https://images-na.ssl-images-amazon.com/images/I/514axA2lwpL.jpg");
        productRepository.save(tempproduct1);

        tempproduct1 = new Product("How to Write a KILLER LinkedIn Profile... And 18 Mistakes to Avoid",
                "Author Brenda Bernstein, Founder and Senior Editor at The Essay Expert LLC, is a #1 bestselling author, in-demand speaker & consultant, and award-winning resume writer.",18.99, 7);
        tempproduct1.setCategory (tempcategory);
        tempproduct1.setImg ("https://m.media-amazon.com/images/I/71AFT1g7kZL._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct1);

        tempproduct1 = new Product("Ladders 2019 Interviews Guide",
                "Based on 15 years experience, the feedback from millions of readers, and over 100,000 HR professionals, recruiters, and hiring managers.",14.99, 3);
        tempproduct1.setCategory (tempcategory);
        tempproduct1.setImg ("https://images-na.ssl-images-amazon.com/images/I/414if654kfL._SX322_BO1,204,203,200_.jpg");
        productRepository.save(tempproduct1);

        tempproduct1 = new Product("What Color Is Your Parachute?",
                "A Practical Manual for Job-Hunters and Career-Changers",14.99, 7);
        tempproduct1.setCategory (tempcategory);
        tempproduct1.setImg ("https://m.media-amazon.com/images/I/71HZYFfwiAL._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct1);


        // new category-clothing
        tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1565028492/4d7b2607c25d8112bc0ae2fcb6a452b6--girl-style-my-style_r2dfge.jpg");
        tempcategory.setTitle("Clothing");
        categoryRepository.save(tempcategory);
        catid = tempcategory.getId();

        Product tempproduct2 = new Product("Black Faux Leather Jacket",
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

        tempproduct2 = new Product("Goodthreads Men's 5-Pack Patterned Socks",
                "Five pairs of lightweight, crew-length, fine gauge, soft cotton blend dress socks",
                14.99, 9);
        tempproduct2.setCategory (tempcategory);
        tempproduct2.setImg ("https://m.media-amazon.com/images/I/A11v3c+583L._AC_UL480_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct2);

        tempproduct2 = new Product("Bolter 4 Pack Men's Everyday Cotton Blend Short Sleeve T-Shirt",
                "Lightweight Everyday Shirt; Great Undershirt for Layering or For Wearing On It's Own in Warmer Climates",
                29.99, 9);
        tempproduct2.setCategory (tempcategory);
        tempproduct2.setImg ("https://m.media-amazon.com/images/I/710UUUPgSOL._AC_UL480_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct2);

        // new category - home appliances
        tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1565028693/0c13ca21c3fc589224a0a614d7d397c1_zepg37.jpg");
        tempcategory.setTitle("Home Appliances ");
        categoryRepository.save(tempcategory);
        catid = tempcategory.getId();


        Product tempproduct3 = new Product("Home/Office PowerPort strip with long 6ft Cord for Smartphone Tablets Home Appliances",
                "UL listed #14 power cord（E312632）assure your family safety. Built-in over-current, over-voltage, over-heat, short-circuit protection; independent safe guard provides premium protection for both home and professional workstations.",
                15.99, 7);
        tempproduct3.setCategory (tempcategory);
        tempproduct3.setImg ("https://m.media-amazon.com/images/I/51imDGx2UwL._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct3);

        tempproduct3 = new Product("Cuisinart ICE-21 Frozen Yogurt-Ice Cream Maker",
                "New mixing paddle makes frozen desserts in 20 minutes or less\n" +
                        "Large capacity makes up to 1-1/2-quarts\n" +
                        "Double insulated freezer bowl eliminates the need for ice",
                42.99, 3);
        tempproduct3.setCategory (tempcategory);
        tempproduct3.setImg ("https://m.media-amazon.com/images/I/61NZiHE74cL._AC_UL480_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct3);

        tempproduct3 = new Product("AmazonBasics 8-Sheet Strip-Cut Paper, CD and Credit Card Home Office ",
                "Strip-cut paper shredder with 8-sheet capacity (no need to remove staples); destroys credit cards, CDs, and DVDs (one at a time)",
                34.99, 3);
        tempproduct3.setCategory (tempcategory);
        tempproduct3.setImg ("https://m.media-amazon.com/images/I/81HM1ZlI23L._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct3);

        tempproduct3 = new Product("MEIYIN New Mini Portable USB Charging Fan Rotating Silent Clip Fan Cooler",
                "Made of high-quality material, durable, firm and nontoxic for us to use.\n" +
                        "With clip design, convenient and easy for us to use, and a wider range of uses.",
                19.99, 15);
        tempproduct3.setCategory (tempcategory);
        tempproduct3.setImg ("https://m.media-amazon.com/images/I/614sCKxSIqL._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct3);

        tempproduct3 = new Product("Hamilton Beach FlexBrew Coffee Maker",
                "brew a full pot using your favorite grounds on the carafe side, or make a cup for yourself using a K-Cup* Pod",
                79.99, 5);
        tempproduct3.setCategory (tempcategory);
        tempproduct3.setImg ("https://m.media-amazon.com/images/I/71bsG3RiY2L._AC_UL480_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct3);

        // new category - electronic devices
        tempcategory = new Category();
        tempcategory.setImg("https://res.cloudinary.com/dgmyjncc8/image/upload/v1565028858/bg1_thlwhk.jpg");
        tempcategory.setTitle("Electronic Devices");
        categoryRepository.save(tempcategory);
        catid = tempcategory.getId();

        Product tempproduct4 = new Product("Mini Projector",
                "Supported resolution:1920*1080 with projector distance 1.5m-5m.",
                99.99, 7);
        tempproduct4.setCategory (tempcategory);
        tempproduct4.setImg ("https://m.media-amazon.com/images/I/71xyQM1Lj8L._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct4);

        tempproduct4 = new Product("RENPHO Bluetooth Body Fat Scale Smart BMI Scale",
                "13 Essential Measurements: Scale shows body weight, data including Weight, BMI, Body Fat Percentage stores to app.",
                25.99, 2);
        tempproduct4.setCategory (tempcategory);
        tempproduct4.setImg ("https://m.media-amazon.com/images/I/61LtAHcbL4L._AC_UL480_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct4);

        tempproduct4 = new Product("Criacr Bluetooth FM Transmitter for Car",
                "Bluetooth FM Transmitter for Car, Wireless FM Radio Transmitter Adapter Car Kit, Dual USB Charging Ports, Hands Free Calling, U Disk, TF Card MP3 Music Player",
                15.99, 3);
        tempproduct4.setCategory (tempcategory);
        tempproduct4.setImg ("https://m.media-amazon.com/images/I/61E-vQI1UgL._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct4);

        tempproduct4 = new Product("PlayStation 4 Slim 1TB Console",
                "Incredible games. Endless entertainment. All new lighter slimmer PS4 with 1TB hard drive.",
                249.99, 2);
        tempproduct4.setCategory (tempcategory);
        tempproduct4.setImg ("https://m.media-amazon.com/images/I/71PGvPXpk5L._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct4);

        tempproduct4 = new Product("FullHD 1080p WiFi Home Security Camera Pan/Tilt/Zoom",
                "Best Rated Smart App, Work with Alexa - Wireless IP Indoor Surveillance System - Night Vision, Remote Baby Monitor iOS (Black)",
                49.99, 17);
        tempproduct4.setCategory (tempcategory);
        tempproduct4.setImg ("https://m.media-amazon.com/images/I/7139JCwm85L._AC_UL654_SEARCH212385_FMwebp_QL65_.jpg");
        productRepository.save(tempproduct4);

        // create test order history: standby status for user 3
        // cancel=1; standby=2; ordered=3; shipped=4; wish = 5; cancelAdmin=6

        OrderHistory tmp = new OrderHistory();
        tmp.setOrderId("08022019001");
        tmp.setOrduser(user3);
        Set<Product> products = new HashSet<Product>();
        products.add(tempproduct1);
        tmp.setProducts(products);
        tmp.setQty(1);
        tmp.setStatus(ORDSTANDBY);
        orderHistoryRepository.save(tmp);

        // create test order history: ordered status for user 3

        tmp = new OrderHistory();
        tmp.setOrderId("08022019001");
        products = new HashSet<Product>();
        products.add(tempproduct2);
        tmp.setProducts(products);
        tmp.setQty(1);
        tmp.setStatus(ORDORDERED);
        tmp.setOrduser(user3);
        orderHistoryRepository.save(tmp);

        // create test order history: ordered status for user 1
        tmp = new OrderHistory();
        tmp.setOrderId("testing003");
        products = new HashSet<Product>();
        products.add(tempproduct3);
        tmp.setProducts(products);
        tmp.setQty(2);
        tmp.setStatus(ORDORDERED);
        tmp.setOrduser(user3);
        orderHistoryRepository.save(tmp);

        tmp = new OrderHistory();
        tmp.setOrderId("testing004");
        products = new HashSet<Product>();
        products.add(tempproduct3);
        tmp.setProducts(products);
        tmp.setQty(2);
        tmp.setStatus(ORDSTANDBY);   // standby status
        tmp.setOrduser(user1);
        orderHistoryRepository.save(tmp);

        tmp = new OrderHistory();
        tmp.setOrderId("testing005");
        products = new HashSet<Product>();
        products.add(tempproduct4);
        tmp.setProducts(products);
        tmp.setQty(2);
        tmp.setStatus(ORDWISH);  // wish status
        tmp.setOrduser(user1);
        orderHistoryRepository.save(tmp);

        // create test order history: open status for user 2
        tmp = new OrderHistory();
        tmp.setOrderId("testing006");
        products = new HashSet<Product>();
        products.add(tempproduct1);
        tmp.setProducts(products);
        tmp.setQty(2);
        tmp.setStatus(ORDORDERED);
        tmp.setOrduser(user2);
        orderHistoryRepository.save(tmp);

        tmp = new OrderHistory();
        tmp.setOrderId("testing007");
        products = new HashSet<Product>();
        products.add(tempproduct2);
        tmp.setProducts(products);
        tmp.setQty(2);
        tmp.setStatus(ORDWISH);
        tmp.setOrduser(user2);
        orderHistoryRepository.save(tmp);



    }
}
