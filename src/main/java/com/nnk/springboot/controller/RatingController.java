package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.service.implentation.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;

    private static Logger logger = LogManager.getLogger(RatingController.class);

    //---------Get-----/rating/list-----------------------------------------------------------
    @RequestMapping("/rating/list")
    public String home(Model model) {
        logger.info(" --> Launch /rating/list" );
        model.addAttribute("ratings", ratingService.getAllRating());
      boolean adminSession = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ADMIN]");
        if (adminSession){
            logger.info("  --> Launch /rating/list ** Admin Session ** " + adminSession );
            model.addAttribute("admin", "admin");
        }

        return "rating/list";
    }
    //----------Get------/rating/add-----------------------------------------------------------
    @GetMapping("/rating/add")
    public String addRatingForm(RatingDTO ratingDTO) {
        logger.info( "--> Launch /rating/add" );
        return "rating/add";
    }
    //---------Post-----/rating/validate----------------------------------------------------------
    @PostMapping("/rating/validate")
    public String validate(@Valid RatingDTO ratingDTO, BindingResult result, Model model) {
        logger.info( "--> Launch /rating/validate");
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            return "rating/add";
        }
        ratingService.addRating(ratingDTO);
        logger.info( "  --> **  Trade saved **");
        return "redirect:/rating/list";
    }
    //----------Get-----/rating/update/{id}----------------------------------------------------------
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /rating/update/{id} with id: " + id);
        RatingDTO ratingDTO = ratingService.getRatingById(id);
        model.addAttribute("ratingDTO", ratingDTO);
        return "rating/update";
    }
    //----------Post-----/rating/update/{id}----------------------------------------------------------
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid RatingDTO ratingDTO,
                               BindingResult result, Model model) {
        logger.info( "--> Launch Post rating/update/{id} with id: " + id);
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            model.addAttribute("ratingDTO", ratingDTO);
            model.addAttribute(id);
            return "rating/update";
        }
        ratingService.updateRating(ratingDTO, id);
        logger.info( "  --> **  Rating updated ** id: " + id);
        return "redirect:/rating/list";
    }
    //----------Get----/rating/delete/{id}--------------------------------------------------------------------------------------
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /rating/delete/{id} + id: " + id );
        ratingService.deleteRating(id);
        logger.info( "  --> **  Rating Deleted ** id: " + id);
        return "redirect:/rating/list";
    }
}
