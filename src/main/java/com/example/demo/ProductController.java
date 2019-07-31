package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* This Controller will deal with all templates related to displaying product information
* */

@Controller
public class ProductController {

    @Autowired
    private UserService userService;

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
    * This page will display a list of the products under a user-chosen category
    */
    @RequestMapping("/listproducts/{id}")
    public String showProducts(@PathVariable("id") long id, Model model){

        //This statement instantiates a category object based on the id of the category chosen.

        Category category = categoryRepository.findById (id).get();

        //This populates a Model object with all the products to be displayed to the user.

        model.addAttribute ( "allproducts", category.getProducts ());
        return "listproducts";          /*Will change names later*/
    }






}
