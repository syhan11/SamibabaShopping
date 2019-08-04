package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

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
        Category category = categoryRepository.findById(id).get();
        model.addAttribute("category", category);

        //This populates a Model object with all the products to be displayed to the user.
        //model.addAttribute ( "allproducts", category.getProducts ());

        model.addAttribute ("orderhist", new OrderHistory(userService.getUser()));

        model.addAttribute ("orderhist", new OrderHistory());

        return "listproducts";          /*Will change names later*/
    }


    //This PostMapping method will allow the user to add an item to their cart. Jacob
    @PostMapping("/addtocart")
    public String additemtocart(Model model, @ModelAttribute("orderhist") OrderHistory orderhist){



        orderHistoryRepository.save(orderhist);


        return "redirect:/";

        //This block of code is no longer needed since the list of products show the current quantity available.
        /*This block of code will check the quantity available of the product and return an error if the requested amount to order is greater*/
//        Product prod = orderhist.getOrdproduct();

//        if (orderhist.getQty() > prod.getQty ())
//
//            return "listproducts";
//        else {
//            orderHistoryRepository.save (orderhist);
//        }
    }







}
