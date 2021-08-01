package com.nnk.springboot.unitaryTest.dto;

import com.nnk.springboot.dto.UserDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDtoTest {


    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    //---------- All null ---------------------------------------------------------------------------------
    @Test
    public void AllNullTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO(null, null, null, null);
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(4, constraintViolations.size());
    }
    //---------- All Empty ---------------------------------------------------------------------------------
    @Test
    public void AllEmptyTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("", "", "", "");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(7, constraintViolations.size());
    }
    //---------- user good ---------------------------------------------------------------------------------
    @Test
    public void allParamsGoodTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("Flora", "GoodPassWord+-*/?!9871+", "DO", "USER");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(0, constraintViolations.size());
    }
    //---------- username & fullname----------------------------------------------------------------------------------
    @Test
    @Tag("test valid:username & fullname")
    public void namesNotOnlyAlphanumericTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("Flora33", "PasswordConform3+", "Do45", "USER");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(2, constraintViolations.size());
        assertEquals("the name must contain at least 2 characters",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    @Tag("test valid:username & fullname")
    public void namesWithOneCharacTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("a", "PasswordConform3+", "D", "USER");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(2, constraintViolations.size());
        assertEquals("the name must contain at least 2 characters",
                constraintViolations.iterator().next().getMessage());
    }


    //---------- password -----------------------------------------------------------------------------------
    @Test
    @Tag("test valid: password")
    public void passwordIsNullTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("Flora", null, "DO", "USER");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(1, constraintViolations.size());
        assertEquals("Password is mandatory", constraintViolations.iterator().next().getMessage());
    }

    @Test
    @Tag("test valid: password")
    public void passwordIsEmptyTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("Flora", "", "DO", "USER");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(2, constraintViolations.size());
    }

    @Test
    @Tag("test valid: password")
    public void passwordWithOutLowerCaseTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("Flora", "PASSWITHOUTLOWERCASE+3", "DO", "USER");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(1, constraintViolations.size());
        assertEquals("The password must be at least 8 characters long including: " +
                        "1 uppercase letter, 1 number, 1 symbol (@ # $% ^ & + =)",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    @Tag("test valid: password")
    public void passwordWithOutHupperCaseTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("Flora", "passwithoutuppercase+3", "DO", "USER");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(1, constraintViolations.size());
        assertEquals("The password must be at least 8 characters long including: " +
                        "1 uppercase letter, 1 number, 1 symbol (@ # $% ^ & + =)",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    @Tag("test valid: password")
    public void passwordWithOutSymbolTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("Flora", "PassWithOutSymbol3", "DO", "USER");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(1, constraintViolations.size());
        assertEquals("The password must be at least 8 characters long including: " +
                        "1 uppercase letter, 1 number, 1 symbol (@ # $% ^ & + =)",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    @Tag("test valid: password")
    public void passwordWithOutNumberTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("Flora", "PassWithOutNumber+", "DO", "USER");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(1, constraintViolations.size());
        assertEquals("The password must be at least 8 characters long including: " +
                        "1 uppercase letter, 1 number, 1 symbol (@ # $% ^ & + =)",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    @Tag("test valid: password")
    public void passwordGoodTest() {
        // GIVEN
        UserDTO userDTO = new UserDTO("Flora", "GoodPassWord+-*/?!9871+", "DO", "USER");
        // WHEN
        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);
        // THEN
        assertEquals(0, constraintViolations.size());
    }
}
