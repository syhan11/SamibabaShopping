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
import java.util.*;

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
    static double MDTAX = 0.05;
    static double DELIVERYCHRG = 9.00;
    static double DELIVERYMIN = 50.00;

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

        model.addAttribute("nocartitems",
                           orderHistoryRepository.countByOrduserEqualsAndStatusEquals(user, ORDSTANDBY));

        return "listproducts";          /*Will change names later*/
    }


    //This PostMapping method will allow the user to add an item to their cart. Jacob
    @RequestMapping("/addtocart/{pid}")
    public String additemtocart(Model model,
                                @PathVariable("pid") long pid,
                                @ModelAttribute("orderhist") OrderHistory orderhist){

        User user = userService.getUser();


        /*
         1. if there isn't any open order
            then create an order history
            else find the first open order
         2. append selected product to the currently opened order
         3. save the order
         */

        ArrayList<OrderHistory> tmporderList = orderHistoryRepository.findAllByOrduserEqualsAndStatusEqualsOrderByOrderId(user, ORDSTANDBY);
        OrderHistory currOrd = new OrderHistory();
        String orderid;

        if (tmporderList.size() == 0) {
            // new order
            SimpleDateFormat date = new SimpleDateFormat("MMddyyyy");
            String dateString = date.format( new Date() );
            int rndNum = (int)(Math.random() * 999) + 99;
            orderid = dateString+String.valueOf(rndNum);
        }
        else {
                orderid = tmporderList.get(0).getOrderId();
        }

        currOrd.setOrderId(orderid);
        currOrd.setStatus(ORDSTANDBY);
        currOrd.setOrduser(user);

        Product currProd = productRepository.findById(pid);
        currOrd.setOrderId(orderid);
        currOrd.setStatus(ORDSTANDBY);
        currOrd.setOrduser(user);
        currOrd.setQty(orderhist.getQty());
        currOrd.setOrdprod(currProd);

        orderHistoryRepository.save(currOrd);


        return "redirect:/";
    }

    @RequestMapping("/viewcart/{username}")
    public String viewcart(Model model){
        User current = userService.getUser();
        ArrayList<OrderHistory> myorders = orderHistoryRepository.findAllByOrduserEqualsAndStatusEqualsOrderByOrderId(current, ORDSTANDBY);
        model.addAttribute("myorders", myorders);
        double tax = 0.0, delivery = 0.0, subtotal = 0.0;

        for (OrderHistory oneord : myorders) {
            subtotal = subtotal + (oneord.getQty() * oneord.getOrdprod().getPrice());
        }

        tax = subtotal * MDTAX;
        if (subtotal >= DELIVERYMIN)
            delivery = 0.0;
        else
            delivery = DELIVERYCHRG;
String taxstr = String.format("%.2f", tax);


        model.addAttribute("tax", String.format("%.2f", tax));
        model.addAttribute("delivery", String.format("%.2f", delivery));
        model.addAttribute("subtotal", String.format("%.2f", subtotal));
        model.addAttribute("total", String.format("%.2f", subtotal+tax+delivery));

        /*
         * FOR USER, NOT ADMIN - need work
         */
        //model.addAttribute("nocartitems", orderHistoryRepository.countByStatusEquals(ORDORDERED));
//        model.addAttribute("nocartitems", orderHistoryRepository.countByOrduserEqualsAndStatusEquals(current, ORDSTANDBY));


        return "viewcart";
    }


    @RequestMapping("/processorder/{id}")
    public String processorder(Model model, @PathVariable String id){

        User current = userService.getUser();

 //       model.addAttribute("product", productRepository.findById(orderHistoryRepository.findByOrduserEqualsAndStatusEquals(current, 2).getOrdproduct().getId()));

        model.addAttribute("order", orderHistoryRepository.findAllByOrduserEqualsAndStatusEqualsOrderByOrderId(current, ORDSTANDBY));

//        OrderHistory tmp = orderHistoryRepository.findByOrderIdEquals(id);

//
//        if (tmp != null)
//            model.addAttribute("product", tmp.getProducts());



        return "redirect:/";
    }

    @RequestMapping("/checkoutorder/{id}")
    public String checkOutOrder(Model model, @PathVariable String id) {

        return "checkout";
    }

    }
