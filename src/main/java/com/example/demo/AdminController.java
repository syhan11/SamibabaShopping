package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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



    @GetMapping("/addcategory")
    public String adminCategory(Model model) {
        model.addAttribute("newcat", new Category());
        model.addAttribute("allcats", categoryRepository.findAll());

        return "addcategory";
    }

    @PostMapping("/processcategory")
    public String processCategory(@Valid @ModelAttribute("newcat") Category newcat, BindingResult result, Model model) {

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



    @RequestMapping("/adminorder")
    public String adminOrder(Model model) {

        //pass currently logged-in user information to index.html
        User crntuser = userService.getUser();
        if (crntuser != null)
            model.addAttribute("crntuser", crntuser);

        return "adminorder";
    }



}
