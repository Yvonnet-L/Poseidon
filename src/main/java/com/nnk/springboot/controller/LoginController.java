package com.nnk.springboot.controller;


import com.nnk.springboot.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@Controller
public class LoginController {

    private static Logger logger = LogManager.getLogger(LoginController.class);

    @GetMapping("/login")
    public String viewLoginPage() {
        logger.info("--> Launch /login ");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "login";
    }
    //-----------------------------------------------------------------------------------------------------------------

}
