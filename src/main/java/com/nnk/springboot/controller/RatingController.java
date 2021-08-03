package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.service.implentation.RatingService;
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

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        return "redirect:/rating/list";
    }
}