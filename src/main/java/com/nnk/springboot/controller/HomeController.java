package com.nnk.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String Home(Model model) {
        return "home";
    }

    @RequestMapping("/admin/home")
    public String adminHome(Model model)
    {
        return "redirect:/bidList/list";
    }

}
