package com.example.demo;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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

    /*
     * after login has been validated, it will come here
     */
    @RequestMapping("/")
    public String homepage(Model model) {
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

        if (current != null) {
            Long userid = current.getId();

            if (current.hasAuthority("ADMIN")) {
                model.addAttribute("nocartitems", orderHistoryRepository.countByStatusEquals(1));

            }
            else
                model.addAttribute("nocartitems", orderHistoryRepository.countByUserIdEqualsAndStatusEquals(userid, 1));
        }
        return "homepage";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {

        return "admin";
    }



}
