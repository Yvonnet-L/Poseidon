package com.nnk.springboot.unitaryTest.controller;

import com.nnk.springboot.controller.JoinController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.DataAlreadyExistException;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.implentation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = JoinController.class)
@AutoConfigureMockMvc
public class JoinControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private UserDTO user1DTO;

    @BeforeEach
    public void setUp() {
        user1DTO = new UserDTO("userNameOne", "PassWordCorrect-4", "fullNameOne", "USER");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //--------Get-------/join--------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on join")
    public void testviewJoinPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/join"))
                .andExpect(view().name("join"))
                .andExpect(status().isOk());
    }
    //--------Post------/join/validate---------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post validate")
    public void testValidateWithAllParamsOk() throws Exception {
        UserDTO userDTO = new UserDTO( "flora", "PassWord1+correct", "DO", "USER");
        UserDTO  userDTOReturn = new UserDTO( 4,"flora", "PassWord1+correct", "DO", "USER");
        Mockito.when(userService.addUser(userDTO)).thenReturn(userDTOReturn);
        mockMvc.perform(MockMvcRequestBuilders.post("/join/validate")
                        .sessionAttr("userDTO", user1DTO)
                        .param("username", userDTO.getUsername())
                        .param("password", userDTO.getPassword())
                        .param("fullname", userDTO.getFullname())
                        .param("role", userDTO.getRole()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post validate with password not conform")
    public void testValidateWithBadParams() throws Exception {
        UserDTO  userDTO = new UserDTO( "flora", "PasswordNotConform+", "DO", "USER");
        mockMvc.perform(MockMvcRequestBuilders.post("/join/validate")
                        .sessionAttr("userDTO", userDTO)
                        .param("username", userDTO.getUsername())
                        .param("password", userDTO.getPassword())
                        .param("fullname", userDTO.getFullname())
                        .param("role", userDTO.getRole()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("join"))
                .andReturn();

    }

    @Test
    @DisplayName("Test with user name aldeady exist: --> exception ")
    public void testValidateWithUsernameAlreadyExist() throws Exception {
        UserDTO userDTO = new UserDTO( "flora", "PassWord1+correct", "DO", "USER");
        UserDTO  userDTOReturn = new UserDTO( 4,"flora", "PassWord1+correct", "DO", "USER");
        User  user = new User( 4,"flora", "PassWord1+correct", "DO", "USER");
        Mockito.when(userService.addUser(userDTO)).thenThrow(new DataAlreadyExistException(""));
        Mockito.when(userRepository.findUserByUsername(userDTO.getUsername())).thenReturn(Optional.of(user));
       //Mockito.when(userRepository.findUserByUsername(userDTO.getUsername()).isPresent()).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/join/validate")
                        .sessionAttr("userDTO", userDTO)
                        .param("username", userDTO.getUsername())
                        .param("password", userDTO.getPassword())
                        .param("fullname", userDTO.getFullname())
                        .param("role", userDTO.getRole()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/login"));

    }
}
