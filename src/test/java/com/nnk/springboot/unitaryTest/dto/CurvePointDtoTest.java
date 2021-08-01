package com.nnk.springboot.unitaryTest.dto;


import com.nnk.springboot.dto.CurvePointDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurvePointDtoTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //---------- All Good---------------------------------------------------------------------------------
    @Test
    public void CurvePointDtoAllPasramsGoodTest() {
        // GIVEN
        CurvePointDTO CurvePointDTO = new CurvePointDTO(1 , 12, 12.32, 1.11);
        // WHEN
        Set<ConstraintViolation<CurvePointDTO>> constraintViolations =
                validator.validate(CurvePointDTO);
        // THEN
        assertEquals(0, constraintViolations.size());
    }
    //---------- All null ---------------------------------------------------------------------------------
    @Test
    public void AllNullTest() {
        // GIVEN
        CurvePointDTO CurvePointDTO = new CurvePointDTO(null , null, null, null);
        // WHEN
        Set<ConstraintViolation<CurvePointDTO>> constraintViolations =
                validator.validate(CurvePointDTO);
        // THEN
        assertEquals(3, constraintViolations.size());
    }
    //---------- All Empty ---------------------------------------------------------------------------------
    @Test
    public void ParamsNotCorrectTest() {
        // GIVEN
        CurvePointDTO CurvePointDTO = new CurvePointDTO(1 , 2, 15.566, 12345678901.45);
        // WHEN
        Set<ConstraintViolation<CurvePointDTO>> constraintViolations =
                validator.validate(CurvePointDTO);
        // THEN
        assertEquals(2, constraintViolations.size());
    }

}
