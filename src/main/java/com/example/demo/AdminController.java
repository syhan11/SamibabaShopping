package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/*
 * This AdminController deals with admin's functionalities
 */

@Controller
public class AdminController {
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

    @Autowired
    EmailService emailService;

    // cancel=1; standby=2; ordered=3; shipped=4; wish = 5; cancelAdmin=6
    static int ORDCANCEL = 1;
    static int ORDSTANDBY = 2;
    static int ORDORDERED = 3;
    static int ORDSHIPPED = 4;
    static int ORDWISH = 5;
    static int ORDADMCANCEL = 6;



    @GetMapping("/addcategory")
    public String adminCategory(Model model) {
        model.addAttribute("newcat", new Category());
        model.addAttribute("user", userService.getUser());
        model.addAttribute("allcats", categoryRepository.findAll());

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

            if (current.hasAuthority("ADMIN")) {
                model.addAttribute("nocartitems", orderHistoryRepository.countByStatusEquals(ORDORDERED));
            }
            else {
                User tmpuser = userRepository.findByUsername(name);
                model.addAttribute("nocartitems",
                                   orderHistoryRepository.countByOrduserEqualsAndStatusEquals(tmpuser, ORDSTANDBY));
            }
        }

        return "addcategory";
    }

    @PostMapping("/processcategory")
    public String processCategory(@Valid @ModelAttribute("category") Category newcat, BindingResult result, Model model) {

        if (result.hasErrors())
            return "/addcategory";
        else {

            categoryRepository.save(newcat);
            return "redirect:/";
        }
    }

    @GetMapping("/addproduct")
    public String addProduct(Model model) {
        model.addAttribute("newprod", new Product());
        model.addAttribute("allcategories", categoryRepository.findAll());

        /*
         * FOR ADMIN - number of items on the cart menu is the total number of all OPEN orders
         */
        model.addAttribute("nocartitems", orderHistoryRepository.countByStatusEquals(ORDORDERED));

        return "addproduct";
    }

    @PostMapping("/processproduct")
    public String processProduct(@Valid @ModelAttribute("newprod") Product newprod, BindingResult result, Model model) {

        if (result.hasErrors())
            return "/addproduct";
        else {
            productRepository.save(newprod);
            return "redirect:/";
        }
    }


    @RequestMapping("/listopenorders")
    public String listOpenOrders(Model model) {
        /*
         * FOR ADMIN - number of items on the cart menu is the total number of all OPEN orders
         */

        model.addAttribute("nocartitems", orderHistoryRepository.countByStatusEquals(ORDORDERED));
        model.addAttribute("allopenorders", orderHistoryRepository.findAllByStatusEqualsOrderByOrderId(ORDORDERED));

        return "listopenorders";
    }

    @RequestMapping("/packageorder/{ordid}")
    public String processOrder(@PathVariable("ordid") String ordid, Model model) {
        String orderNo = "not available";
        String orderEmail = null;

        ArrayList<OrderHistory> allorders = orderHistoryRepository.findAllByOrderIdEquals(ordid);
        for (OrderHistory crntorder : allorders) {
            // calculate total sum of the order

            // for all products on this order, set status to ORDSHIPPED
            crntorder.setStatus(ORDSHIPPED);
            orderHistoryRepository.save(crntorder);

            if (orderEmail == null) {
                orderNo = crntorder.getOrderId();
                orderEmail = crntorder.getOrduser().getEmail();
            }
        }

        // send out an email
        emailService.SendSimpleEmail(orderEmail, orderNo);


        return "redirect:/listopenorders";

    }

    @RequestMapping("/detailorder/{ordid}")
    public String detailOrder(@PathVariable("ordid") String ordid, Model model) {
        OrderHistory crntorder = orderHistoryRepository.findByOrderIdEquals(ordid);

        // need to call a method to send out an email
        return "redirect:/";

    }

}
