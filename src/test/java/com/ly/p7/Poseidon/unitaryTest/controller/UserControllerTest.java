package com.ly.p7.Poseidon.unitaryTest.controller;


import com.ly.p7.Poseidon.controller.UserController;
import com.ly.p7.Poseidon.domain.User;
import com.ly.p7.Poseidon.dto.UserDTO;
import com.ly.p7.Poseidon.repositories.UserRepository;
import com.ly.p7.Poseidon.security.MyUserDetailService;
import com.ly.p7.Poseidon.service.implentation.UserService;
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

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserDetailService userDetailsService;

    @Autowired
    private WebApplicationContext context;

    private UserDTO user1DTO;

    private UserDTO user2DTO;

    @BeforeEach
    public void setUp() {
        user1DTO = new UserDTO("userNameOne", "PassWordCorrect-4", "fullNameOne", "USER");
        user2DTO = new UserDTO("userNameTwo", "PassWordCorrect-4", "fullNameTwo", "USER");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    //-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on getUser")
    public void testGetUsers() throws Exception {
        Mockito.when(userService.getAllUser()).thenReturn(Arrays.asList(user1DTO, user2DTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/list"))
               .andExpect(model().attributeExists("users"))
               .andExpect(view().name("user/list"))
                .andExpect(status().isOk());

    }
    //--------Get-------/user/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on updateUser")
    public void testUpdateUser() throws Exception {
        Mockito.when(userService.getUserById(1)).thenReturn(user1DTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/update/1"))
                .andExpect(status().isOk());
    }
    //--------Post-------/user/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post showUpdateForm")
    public void testShowUpdateFormWithUserDTOConform() throws Exception {
        UserDTO  userDTO = new UserDTO( "flora", "PassWord1+correct", "DO", "USER");
        Mockito.when(userService.updateUser(user1DTO,1)).thenReturn(userDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update/1")
                        .sessionAttr("userDTO", user1DTO)
                        .param("username", userDTO.getUsername())
                        .param("password", userDTO.getPassword())
                        .param("fullname", userDTO.getFullname())
                        .param("role", userDTO.getRole()))
                        .andExpect(model().hasNoErrors())
                        .andExpect(redirectedUrl("/user/list"))
                        .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post showUpdateForm with userDTO not conform, with number in the name" )
    public void testShowUpdateFormWithUserDTONotConform() throws Exception {
        UserDTO  userDTO = new UserDTO( "flora3333", "PassWord1+correct", "DO", "USER");
        Mockito.when(userService.updateUser(user1DTO,1)).thenReturn(userDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update/1")
                .sessionAttr("userDTO", user1DTO)
                .param("username", userDTO.getUsername())
                .param("password", userDTO.getPassword())
                .param("fullname", userDTO.getFullname())
                .param("role", userDTO.getRole()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("user/update"))
                .andReturn();
    }
    //--------Get-------/user/add---------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on addUser")
    public void testAddUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/add"))
                .andExpect(view().name("user/add"))
                .andExpect(status().isOk());
    }
    //--------Post------/user/validate---------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post validate")
    public void testValidateWithAllParamsOk() throws Exception {
        UserDTO  userDTO = new UserDTO( "flora", "PassWord1+correct", "DO", "USER");
        UserDTO  userDTOReturn = new UserDTO( 4,"flora", "PassWord1+correct", "DO", "USER");
        Mockito.when(userService.addUser(userDTO)).thenReturn(userDTOReturn);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .sessionAttr("userDTO", user1DTO)
                .param("username", userDTO.getUsername())
                .param("password", userDTO.getPassword())
                .param("fullname", userDTO.getFullname())
                .param("role", userDTO.getRole()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post validate with password not conform without number")
    public void testValidateWithBadParamsWithoutNumber() throws Exception {
        UserDTO  userDTO = new UserDTO( "flora", "PasswordNotConform+", "DO", "USER");

        mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .sessionAttr("userDTO", user1DTO)
                .param("username", userDTO.getUsername())
                .param("password", userDTO.getPassword())
                .param("fullname", userDTO.getFullname())
                .param("role", userDTO.getRole()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("user/add"))
                .andReturn();

    }

    @Test
    @DisplayName("Test Post validate with password not conform without symbol")
    public void testValidateWithBadParamsPasswordWithoutSymbol() throws Exception {
        UserDTO  userDTO = new UserDTO( "flora", "PasswordNotConform3", "DO", "USER");

        mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .sessionAttr("userDTO", user1DTO)
                .param("username", userDTO.getUsername())
                .param("password", userDTO.getPassword())
                .param("fullname", userDTO.getFullname())
                .param("role", userDTO.getRole()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("user/add"))
                .andReturn();

    }

    @Test
    @DisplayName("Test Post validate with password not conform without Uppercase")
    public void testValidateWithBadParamsPasswordWithoutUppercase() throws Exception {
        UserDTO  userDTO = new UserDTO( "flora", "passwordnonconform3+", "DO", "USER");

        mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .sessionAttr("userDTO", user1DTO)
                .param("username", userDTO.getUsername())
                .param("password", userDTO.getPassword())
                .param("fullname", userDTO.getFullname())
                .param("role", userDTO.getRole()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("user/add"))
                .andReturn();
    }

    @Test
    @DisplayName("Test Post validate with password not conform without lowercase")
    public void testValidateWithBadParamsPasswordWithoutLowercase() throws Exception {
        UserDTO  userDTO = new UserDTO( "flora", "PASSWORDNOTCONFORM3+", "DO", "USER");

        mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .sessionAttr("userDTO", user1DTO)
                .param("username", userDTO.getUsername())
                .param("password", userDTO.getPassword())
                .param("fullname", userDTO.getFullname())
                .param("role", userDTO.getRole()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("user/add"))
                .andReturn();
    }

    //--------Get------/user/delete/{id}---------------------------------------------------------------------------------------------

    @Test
    @DisplayName("Test Get deleteUser responce Ok/redirect")
    public void testDeleteUser() throws Exception{
        User user = new User(5,"userNameOne", "PassWordCorrect-4", "fullNameOne", "USER");
        Mockito.when(userRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.ofNullable(user));
        userService.deleteUser(any(Integer.class));
        //verify(userRepository).deleteById(any(Integer.class));
    }
}
