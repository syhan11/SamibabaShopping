package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/*
 * This controller will deal with all but security (login & register)
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @Autowired
    OrderHistoryRepository orderHistoryRepository;

    // FOR ORDER STATUS: cancel=1; standby=2; ordered=3; shipped=4; wish = 5; cancelAdmin=6
    static int ORDCANCEL = 1;
    static int ORDSTANDBY = 2;
    static int ORDORDERED = 3;
    static int ORDSHIPPED = 4;
    static int ORDWISH = 5;
    static int ORDADMCANCEL = 6;

    /*
     * after login has been validated, it will come here
     */
    @RequestMapping("/")
    public String homepage(Model model, User user) {
        model.addAttribute("categories", categoryRepository.findAll());

        /*
         * FOR ADMIN
         *     number of items on the cart menu is the total number of all OPEN orders
         * FOR OTHERS (like USER)
         *     number of items on the cart menu is the total number of user's OPEN orders
         *
         * number of items on the cart menu needs to be passed as 'nocartitems' to html
         *
         */
        User current = userService.getUser();
        Long userid;

        if (current != null) {
            userid = current.getId();
            String name = current.getUsername();
            String empty; //Just to get rid of ugly wavy warning because it is a duplicate

            if (current.hasAuthority("ADMIN")) {
                model.addAttribute("nocartitems", orderHistoryRepository.countByStatusEquals(ORDORDERED));

            }
            else {
//                model.addAttribute("nocartitems", orderHistoryRepository.countByUserIdEqualsAndStatusEquals(userid, 1));
                User tmpuser = userRepository.findByUsername(name);
                model.addAttribute("nocartitems",orderHistoryRepository.countByOrduserEqualsAndStatusEquals(tmpuser, ORDSTANDBY));
            }
        }

//        model.addAttribute("nocartitems", orderHistoryRepository.countByStatusEquals(ORDORDERED));
        return "homepage";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {

        return "search";
    }

    @RequestMapping("/search")
    public String processRegistrationPage(@ModelAttribute("keyword") String keyword,
                                          Model model) {
        ArrayList<Product> searchresult = new ArrayList<Product>();
        ArrayList<Product> sear = productRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);


        // only unique products
        for (Product element : sear) {

            // If this element is not present in newList
            // then add it
            if (!searchresult.contains(element)) {

                searchresult.add(element);
            }
        }

        User tmpuser = userService.getUser();
        Long userid;

        if (tmpuser != null) {
            userid = tmpuser.getId();
            String name = tmpuser.getUsername();

            if (tmpuser.hasAuthority("ADMIN")) {
                model.addAttribute("nocartitems", orderHistoryRepository.countByStatusEquals(ORDORDERED));
            }
            else {
                tmpuser = userRepository.findByUsername(name);
                model.addAttribute("nocartitems",orderHistoryRepository.countByOrduserEqualsAndStatusEquals(tmpuser, ORDSTANDBY));
            }
        }


        model.addAttribute("searchresult", searchresult);
        model.addAttribute ("orderhist", new OrderHistory(userService.getUser()));

        return "search";

    }


}
