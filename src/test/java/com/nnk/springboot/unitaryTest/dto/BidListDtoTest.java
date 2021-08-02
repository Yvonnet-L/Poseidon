package com.nnk.springboot.unitaryTest.dto;

import com.nnk.springboot.dto.BidListDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BidListDtoTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    //---------- All Good---------------------------------------------------------------------------------
    @Test
    public void bidListDtoAllPasramsGoodTest() {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(1 , "account1", "type1", 1.11);
        // WHEN
        Set<ConstraintViolation<BidListDTO>> constraintViolations =
                validator.validate(bidListDTO);
        // THEN
        assertEquals(0, constraintViolations.size());
    }
    //---------- All null ---------------------------------------------------------------------------------
    @Test
    public void AllNullTest() {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(null , null, null, null);
        // WHEN
        Set<ConstraintViolation<BidListDTO>> constraintViolations =
                validator.validate(bidListDTO);
        // THEN
        assertEquals(2, constraintViolations.size());
    }
    //---------- All Empty ---------------------------------------------------------------------------------
    @Test
    public void AllEmptyTest() {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(null , "", "", null);
        // WHEN
        Set<ConstraintViolation<BidListDTO>> constraintViolations =
                validator.validate(bidListDTO);
        // THEN
        assertEquals(2, constraintViolations.size());
    }

    @Test
    public void AllEmpty2Test() {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(null , "  ", "  ", null);
        // WHEN
        Set<ConstraintViolation<BidListDTO>> constraintViolations =
                validator.validate(bidListDTO);
        // THEN
        assertEquals(2, constraintViolations.size());
    }
}
