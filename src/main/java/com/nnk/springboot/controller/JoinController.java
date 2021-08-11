package com.nnk.springboot.controller;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.DataAlreadyExistException;
import com.nnk.springboot.service.interfaces.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


/**
 *  JoinController is used to manage requests on the Join page, a new user can create an account to connect
 *  to the application, this account will only have one user authority. The intervention of an Admin will be
 *  necessary for up this account.
 */

@Controller
public class JoinController {

    @Autowired
    private IUserService userService;

    private static Logger logger = LogManager.getLogger(JoinController.class);

    //-----------------------------------------------------------------------------------------------------------------
    @GetMapping("/join")
    public String viewJoinPage(UserDTO userDTO) {
        logger.info( "--> Launch /join" );
        return "join";

    }
    //-----------------------------------------------------------------------------------------------------------------
    @PostMapping("/join/validate")
    public String validate(@Valid UserDTO userDTO, BindingResult result, Model model) {
        logger.info( "--> Launch /join/validate" );
        if (!result.hasErrors()) {
            try {
                UserDTO userCreatedDTO = userService.addUser(userDTO);
                logger.info("  --> **  User created ** with id: " + userCreatedDTO.getId());
                return "redirect:/login";
            }
            catch (DataAlreadyExistException e){
                model.addAttribute("error", e);
                return "join";
            }
        }
        logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
        return "join";
    }
}
