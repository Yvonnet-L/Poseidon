package com.ly.p7.Poseidon.conttoller;

import com.ly.p7.Poseidon.domain.User;
import com.ly.p7.Poseidon.dto.UserDTO;
import com.ly.p7.Poseidon.repositories.UserRepository;
import com.ly.p7.Poseidon.service.interfaces.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            logger.info( " --->   user/validate - OK -" );
            return "redirect:/user/list";
        }
        return "user/add";
    }
    //-----------------------------------------------------------------------------------------------------------------
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }
    //-----------------------------------------------------------------------------------------------------------------
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        logger.info( "--> Launch /user/update/{id} + id: " + id );
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        logger.info( "-->   user/update id: " + id + " - SUCCESS -" );
        return "redirect:/user/list";
    }
    //-----------------------------------------------------------------------------------------------------------------
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /user/delete/{id} + id: " + id );
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        logger.info( "-->   user/delete id: " + id + " - SUCCESS -" );
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
    //-----------------------------------------------------------------------------------------------------------------
}
