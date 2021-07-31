package com.nnk.springboot.controller;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.interfaces.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 *  Allows the control of all Crud requests
 *  on the User class link of the users table of the DB
 */

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    private static Logger logger = LogManager.getLogger(UserController.class);

    //-----------------------------------------------------------------------------------------------------------------
    @RequestMapping("/user/list")
    public String userList(Model model)
    {
        logger.info( "--> Launch /user/list" );
        model.addAttribute("users",userService.getAllUser());
        logger.info( " --->   user/list - OK -" );
        return "user/list";
    }
    //-----------------------------------------------------------------------------------------------------------------
    @GetMapping("/user/add")
    public String addUser(UserDTO bid) {
        logger.info( "--> Launch /user/add" );
        return "user/add";
    }
    //-----------------------------------------------------------------------------------------------------------------
    @PostMapping("/user/validate")
    public String validate(@Valid UserDTO userDTO, BindingResult result, Model model) {
        logger.info( "--> Launch /user/validate" );
        if (!result.hasErrors()) {
            userService.addUser(userDTO);
            model.addAttribute("users", userRepository.findAll());
            logger.info( "  --> **  User created ** id: " + userDTO.getId());
            return "redirect:/user/list";
        }
        logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
        return "user/add";
    }
    //-----------------------------------------------------------------------------------------------------------------
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /user/update/{id} with id = " + id );
        UserDTO userDTO  = userService.getUserById(id);
        model.addAttribute("userDTO", userDTO);
        return "user/update";
    }
    //-----------------------------------------------------------------------------------------------------------------
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid UserDTO userDTO,
                             BindingResult result, Model model) {
        logger.info( "--> Launch /user/update/{id} + id: " + id );
        logger.info( "--> Launch /user/update/{id} + userDTO.getId: " + userDTO.getId() );
        if (result.hasErrors()) {
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            return "user/update";
        }
        userService.updateUser(userDTO, userDTO.getId());
        logger.info( "  --> **  User updated ** id: " + id);
        return "redirect:/user/list";
    }
    //-----------------------------------------------------------------------------------------------------------------
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /user/delete/{id} + id: " + id );
        userService.deleteUser(id);
        logger.info( "  --> **  User Deleted ** id: " + id);
        return "redirect:/user/list";
    }
    //-----------------------------------------------------------------------------------------------------------------
}
