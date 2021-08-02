package com.nnk.springboot.controller;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.service.interfaces.ICurvePointService;
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
public class CurveController {

    @Autowired
    private ICurvePointService curvePointService;

    private static Logger logger = LogManager.getLogger(CurveController.class);


    //---------Get-----/curvePoint/list"-------------------------------------------------------
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        logger.info(" --> Launch /curvePoint/list" );
        model.addAttribute("curves", curvePointService.getAllCurvePoint());
        return "curvePoint/list";
    }
    //----------Get------/curvePoint/add-----------------------------------------------------------
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePointDTO curvePointDTO) {
        logger.info( "--> Launch /curvePoint/add" );
        return "curvePoint/add";
    }
    //---------Post-----/curvePoint/validate----------------------------------------------------------
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePointDTO curvePointDTO, BindingResult result, Model model) {
        logger.info( "--> Launch /curvePoint/validate");
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            return "curvePoint/add";
        }
        curvePointService.addCurvePoint(curvePointDTO);
        logger.info( "  --> **  CurvePoint saved **");
        return "redirect:/curvePoint/list";
    }
    //----------Get-----/curvePoint/update/{id}----------------------------------------------------------
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /curvePoint/update/{id} with id: " + id);
        CurvePointDTO curvePointDTO = curvePointService.getCurvePointById(id);
        model.addAttribute("curvePointDTO", curvePointDTO);
        return "curvePoint/update";
    }
    //----------Post-----/curvePoint/update/{id}-------------------------------------------------------------
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePointDTO curvePointDTO,
                            BindingResult result, Model model) {
        logger.info( "--> Launch Post curvePoint/update/{id} with id: " + id);
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            model.addAttribute("curves", curvePointDTO);
            model.addAttribute(id);
            return "curvePoint/update";
        }
        curvePointService.updateCurvePoint(curvePointDTO, id);
        logger.info( "  --> **  CurvePoint updated ** id: " + id);
        return "redirect:/curvePoint/list";
    }
    //----------Get-----/curvePoint/delete/{id}----------------------------------------------------------
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /curvePoint/delete/{id} + id: " + id );
        curvePointService.deleteCurvePoint(id);
        logger.info( "  --> **  CurvePoint Deleted ** id: " + id);
        return "redirect:/curvePoint/list";
    }
}
