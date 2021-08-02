package com.nnk.springboot.unitaryTest.dto;

import com.nnk.springboot.dto.TradeDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //---------- All Good---------------------------------------------------------------------------------
    @Test
    public void tradeDtoAllPasramsGoodTest() {
        // GIVEN
        TradeDTO tradeDTO = new TradeDTO(1 , "account1", "type2", 1.11);
        // WHEN
        Set<ConstraintViolation<TradeDTO>> constraintViolations =
                validator.validate(tradeDTO);
        // THEN
        assertEquals(0, constraintViolations.size());
    }
    //---------- All null---------------------------------------------------------------------------------
    @Test
    public void tradeDtoAllPasramsNullTest() {
        // GIVEN
        TradeDTO tradeDTO = new TradeDTO(null , null, null, null);
        // WHEN
        Set<ConstraintViolation<TradeDTO>> constraintViolations =
                validator.validate(tradeDTO);
        // THEN
        assertEquals(2, constraintViolations.size());
    }
    //---------- Not Conform---------------------------------------------------------------------------------
    @Test
    public void tradeDtoAllParamsNotConformTest() {
        // GIVEN
        TradeDTO tradeDTO = new TradeDTO(1 , "account1", "type1", 12345678901.45);
        // WHEN
        Set<ConstraintViolation<TradeDTO>> constraintViolations =
                validator.validate(tradeDTO);
        // THEN
        assertEquals(1, constraintViolations.size());
    }
}
