package com.nnk.springboot.controller;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.service.interfaces.ITradeService;
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
public class TradeController {

    @Autowired
    ITradeService tradeService;

    private static Logger logger = LogManager.getLogger(TradeController.class);

    //---------Get-----/trade/list-----------------------------------------------------------
    @RequestMapping("/trade/list")
    public String home(Model model) {
        logger.info(" --> Launch /trade/list" );
        boolean adminSession = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ADMIN]");
        if (adminSession){
            logger.info("  --> Launch /trade/list ** Admin Session ** " + adminSession );
            model.addAttribute("admin", "admin");
        }
        model.addAttribute("trades", tradeService.getAllTrade());
        return "trade/list";
    }
    //----------Get------/trade/add-----------------------------------------------------------
    @GetMapping("/trade/add")
    public String addTrade(TradeDTO tradeDTO) {
        logger.info( "--> Launch /trade/add" );
        return "trade/add";
    }
    //---------Post-----/trade/validate----------------------------------------------------------
    @PostMapping("/trade/validate")
    public String validate(@Valid TradeDTO tradeDTO, BindingResult result, Model model) {
        logger.info( "--> Launch /trade/validate");
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            return "trade/add";
        }
        tradeService.addTrade(tradeDTO);
        logger.info( "  --> **  Trade saved **");
        return "redirect:/trade/list";
    }
    //----------Get-----/trade/update/{id}----------------------------------------------------------
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /trade/update/{id} with id: " + id);
        TradeDTO tradeDTO = tradeService.getTradeById(id);
        model.addAttribute("tradeDTO", tradeDTO);
        return "trade/update";
    }
    //----------Post-----/trade/update/{id}-------------------------------------------------------------------------------------
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid TradeDTO tradeDTO,
                              BindingResult result, Model model) {
        logger.info( "--> Launch Post trade/update/{id} with id: " + id);
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            model.addAttribute("tradeDTO", tradeDTO);
            model.addAttribute(id);
            return "trade/update";
        }
        tradeService.updateTrade(tradeDTO, id);
        logger.info( "  --> **  Trade updated ** id: " + id);
        return "redirect:/trade/list";
    }
    //----------Get----/trade/delete/{id}--------------------------------------------------------------------------------------
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /trade/delete/{id} + id: " + id );
        tradeService.deleteTrade(id);
        logger.info( "  --> **  Trade Deleted ** id: " + id);
        return "redirect:/trade/list";
    }
}
