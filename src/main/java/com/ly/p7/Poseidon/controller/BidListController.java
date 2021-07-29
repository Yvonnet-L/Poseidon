package com.ly.p7.Poseidon.controller;


import com.ly.p7.Poseidon.dto.BidListDTO;
import com.ly.p7.Poseidon.service.interfaces.IBidListService;
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
public class BidListController {

    @Autowired
    private IBidListService bidListService;

    private static Logger logger = LogManager.getLogger(BidListController.class);


    //---------Get-----/bidList/list"-------------------------------------------------------------------------------------------
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        logger.info("--> Launch /bidList/list");
        model.addAttribute("bids", bidListService.getAllBidList());
        return "bidList/list";
    }
    //----------Get------/bidList/add------------------------------------------------------------------------------------------
    @GetMapping("/bidList/add")
    public String addBidForm(BidListDTO bid) {
        logger.info( "--> Launch /bidList/add" );
        return "bidList/add";
    }
    //---------Post-----/bidList/validate---------------------------------------------------------------------------------
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidListDTO bid, BindingResult result, Model model) {
        logger.info( "--> Launch /bidList/validate");
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            return "bidList/add";
        }
        bidListService.addBidList(bid);
        logger.info( "  --> **  bidList saved **");
        return "redirect:/bidList/list";
    }
    //----------Get-----/bidList/update/{id}----------------------------------------------------------------------------------------
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /update/{id} with id: " + id);
        BidListDTO bidListDTO = bidListService.getBidListById(id);
        model.addAttribute("bidListDTO", bidListDTO);
        return "bidList/update";
    }
    //----------Post-----/bidList/update/{id}-------------------------------------------------------------------------------------
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidListDTO bidListDTO,
                            BindingResult result, Model model) {
        logger.info( "--> Launch Post bidList/update/{id} with id: " + id);
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            model.addAttribute("bidListDTO", bidListDTO);
            model.addAttribute(id);
            return "bidList/update";
        }
        bidListService.updateBidList(bidListDTO, id);
        logger.info( "  --> **  BidList updated ** id: " + id);
        return "redirect:/bidList/list";
    }
    //----------Get----/bidList/delete/{id}--------------------------------------------------------------------------------------
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /bidList/delete/{id} + id: " + id );
        bidListService.deleteBidList(id);
        logger.info( "  --> **  BidList Deleted ** id: " + id);
        return "redirect:/bidList/list";
    }
    //-------------------------------------------------------------------------------------------------------
}
