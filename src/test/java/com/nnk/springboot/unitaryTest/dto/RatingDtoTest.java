package com.nnk.springboot.unitaryTest.dto;


import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.dto.TradeDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingDtoTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //---------- All Good---------------------------------------------------------------------------------
    @Test
    public void ratingDtoAllPasramsGoodTest() {
        // GIVEN
        RatingDTO ratingDTO = new RatingDTO("moody1" , "sand1", "fitch1", 1);
        // WHEN
        Set<ConstraintViolation<RatingDTO>> constraintViolations =
                validator.validate(ratingDTO);
        // THEN
        assertEquals(0, constraintViolations.size());
    }
    //---------- All null---------------------------------------------------------------------------------
    @Test
    public void ratingDtoAllPasramsNullTest() {
        RatingDTO ratingDTO = new RatingDTO(null , null, null, null);
        // WHEN
        Set<ConstraintViolation<RatingDTO>> constraintViolations =
                validator.validate(ratingDTO);
        // THEN
        assertEquals(3, constraintViolations.size());
    }
    //---------- Not Conform---------------------------------------------------------------------------------
    @Test
    public void ratingDtoAllParamsNotConformTest() {
        RatingDTO ratingDTO = new RatingDTO("moody1" , "sand1", "fitch1", -11);
        // WHEN
        Set<ConstraintViolation<RatingDTO>> constraintViolations =
                validator.validate(ratingDTO);
        // THEN
        assertEquals(1, constraintViolations.size());
    }
}
