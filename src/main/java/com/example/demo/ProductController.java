package com.example.demo;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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

    // cancel=1; standby=2; ordered=3; shipped=4; wish = 5; cancelAdmin=6
    static int ORDCANCEL = 1;
    static int ORDSTANDBY = 2;
    static int ORDORDERED = 3;
    static int ORDSHIPPED = 4;
    static int ORDWISH = 5;
    static int ORDADMCANCEL = 6;


    /*
     * This page will display a list of the products under a user-chosen category
     */
    @RequestMapping("/listproducts/{id}")
    public String showProducts(@PathVariable("id") long id, Model model){
        model.addAttribute("categories", categoryRepository.findAll());

        User user = userService.getUser();

        //This statement instantiates a category object based on the id of the category chosen.
        Category category = categoryRepository.findById(id).get();
        model.addAttribute("category", category);

        //This populates a Model object with all the products to be displayed to the user.
        //model.addAttribute ( "allproducts", category.getProducts ());

        model.addAttribute ("orderhist", new OrderHistory(userService.getUser()));

        model.addAttribute("nocartitems", orderHistoryRepository.countByStatusEquals(ORDORDERED));

        return "listproducts";          /*Will change names later*/
    }


    //This PostMapping method will allow the user to add an item to their cart. Jacob
    @PostMapping("/addtocart")
    public String additemtocart(Model model, @ModelAttribute("orderhist") OrderHistory orderhist){

        User user = userService.getUser();

        SimpleDateFormat date = new SimpleDateFormat("MMddyyyy");

        String dateString = date.format( new Date() );

        model.addAttribute("date", dateString);

        int rndNum = (int)(Math.random() * 999) + 99;
        String orderid = dateString+String.valueOf(rndNum);

        orderhist.setOrderId(orderid);

        orderhist.setStatus(2);

        orderhist.setOrduser(user);


        //Tried to get the same orderid for multiple items
        if ((orderHistoryRepository.countByOrduserEqualsAndStatusEquals(user, 2) != 0) && (orderHistoryRepository.countByOrduserEqualsAndOrderIdNotContaining(user, "test") != 0)){



            orderHistoryRepository.save(orderhist);

            return "redirect:/";
        } else {
            orderHistoryRepository.save(orderhist);
        }

        return "redirect:/";
    }

    @RequestMapping("/viewcart/{username}")
    public String viewcart(Model model){
        model.addAttribute("categories", categoryRepository.findAll());

        User current = userService.getUser();

//        model.addAttribute("myorders", orderHistoryRepository.findAllByOrduserEqualsAndStatusEquals(current, ORDSTANDBY));

        /*
         * FOR USER, NOT ADMIN - need work
         */
        //model.addAttribute("nocartitems", orderHistoryRepository.countByStatusEquals(ORDORDERED));
//        model.addAttribute("nocartitems", orderHistoryRepository.countByOrduserEqualsAndStatusEquals(current, ORDSTANDBY));

        ArrayList<OrderHistory> temp = orderHistoryRepository.findAllByOrduser(current);
        ArrayList<OrderHistory> searchresult = new ArrayList<OrderHistory>();

// only unique products
        for (OrderHistory element : temp) {


            if ((element.getStatus() == ORDSTANDBY) && (element.getId() != 0)) {

                searchresult.add(element);
            }
        }
        model.addAttribute("myorders", searchresult);
        model.addAttribute("nocartitems",searchresult.size());




        return "viewcart";
    }


    @RequestMapping("/processorder/{id}")
    public String processorder(Model model, @PathVariable String xid){

        User current = userService.getUser();

 //       model.addAttribute("product", productRepository.findById(orderHistoryRepository.findByOrduserEqualsAndStatusEquals(current, 2).getOrdproduct().getId()));

        model.addAttribute("order", orderHistoryRepository.findAllByOrduserEqualsAndStatusEquals(current, 2));

        OrderHistory tmp = orderHistoryRepository.findByOrderId(xid);

        model.addAttribute("product", tmp.getOrdproduct());



        return "redirect:/";
    }



}
