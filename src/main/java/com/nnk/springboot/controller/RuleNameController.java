package com.nnk.springboot.controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.service.interfaces.IRuleNameService;
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
public class RuleNameController {

    @Autowired
    IRuleNameService ruleNameService;

    private static Logger logger = LogManager.getLogger(RuleNameController.class);

    //---------Get-----/ruleName/list-----------------------------------------------------------
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        logger.info(" --> Launch /ruleName/list" );
        model.addAttribute("ruleNames", ruleNameService.getAllRuleName());
        return "ruleName/list";
    }
    //----------Get------/ruleName/add-----------------------------------------------------------
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleNameDTO ruleNameDTO) {
        logger.info( "--> Launch /ruleName/add" );
        return "ruleName/add";
    }
    //---------Post-----/trade/validate----------------------------------------------------------
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleNameDTO ruleNameDTO, BindingResult result, Model model) {
        logger.info( "--> Launch /ruleName/validate");
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            return "ruleName/add";
        }
        ruleNameService.addRuleName(ruleNameDTO);
        logger.info( "  --> **  RuleName saved **");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        return "redirect:/ruleName/list";
    }
}
