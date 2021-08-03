package com.nnk.springboot.unitaryTest.service;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.implentation.UserService;
import com.nnk.springboot.tool.DtoBuilder;
import com.nnk.springboot.tool.ModelBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    DtoBuilder dtoBuilder;

    @Mock
    ModelBuilder modelBuilder;

    private List<User> userList;

    //---------- GetAll-----------------------------------------------------------------------------------------------------------------
    @Test
    public void getAllUserTest(){
        // GIVEN
        User user1 = new User(1, "userNameOne", "PassWordCorrect-1", "fullNameOne", "ADMIN");
        User user2 = new User(2, "userNameTwo", "PassWordCorrect-1", "fullNameTwo","USER");
        User user3 = new User(3, "userNameTree", "PassWordCorrect-1", "fullNameTree", "USER");
        userList = Arrays.asList(user1, user2, user3);
        // WHEN
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        // THEN
        assertThat(userService.getAllUser().size()).isEqualTo(3);
    }

    //------------AddUser--------------------------------------------------------------------------------------------------------------
    @Test
    public void addUserTest(){
        // GIVEN
        UserDTO userDTO = new UserDTO("userNameFour", "PassWordCorrect-4", "fullNameFour", "ADMIN");
        User userAfterBuilder = new User(null, "userNameFour", "Bcrypt-PassWordCorrect-4", "fullNameFour", "USER");
        User userAfterSaveWithID = new User(4, "userNameOne", "Bcrypt-PassWordCorrect-4", "fullNameOne", "ADMIN");
        UserDTO userDTOResultSaveWithID = new UserDTO(4,"userNameFour", "Bcrypt-PassWordCorrect-4", "fullNameFour", "ADMIN");
        // WHEN
        Mockito.when(modelBuilder.buildUser(userDTO)).thenReturn(userAfterBuilder);
        Mockito.when(userRepository.save(userAfterBuilder)).thenReturn(userAfterSaveWithID);
        Mockito.when(dtoBuilder.buildUserDTO(any(User.class))).thenReturn(userDTOResultSaveWithID);
        // THEN
        assertThat(userService.addUser(userDTO)).isEqualTo(userDTOResultSaveWithID);

    }
    //-------------UpdateUser-------------------------------------------------------------------------------------------------------------
    @Test
    public void updateUserExistTest(){
        // GIVEN
        UserDTO userDTO = new UserDTO(4,"userNameFour", "PassWordCorrect-4", "fullNameFour", "USER");
        User userAfterFindById = new User(4, "userNameOne", "Bcrypt-PassWordCorrect-4", "fullNameOne", "USER");
        User userAfterBuilder = new User(null, "userNameFour", "Bcrypt-PassWordCorrect-4", "fullNameFour", "USER");
        User userAfterSaveWithID = new User(4, "userNameOne", "Bcrypt-PassWordCorrect-4", "fullNameOne", "USER");
        UserDTO userDTOResultSaveWithID = new UserDTO(4,"userNameFour", "Bcrypt-PassWordCorrect-4", "fullNameFour", "USER");
        // WHEN
        Mockito.when(userRepository.findById(4)).thenReturn(java.util.Optional.of(userAfterFindById));
        Mockito.when(modelBuilder.buildUser(userDTO)).thenReturn(userAfterBuilder);
        Mockito.when(userRepository.save(userAfterBuilder)).thenReturn(userAfterSaveWithID);
        Mockito.when(dtoBuilder.buildUserDTO(any(User.class))).thenReturn(userDTOResultSaveWithID);
        // THEN
        assertThat(userService.updateUser(userDTO,4)).isEqualTo(userDTOResultSaveWithID);
    }

    @Test
    public void updateUserNoExistTest(){
        // GIVEN
        UserDTO userDTO = new UserDTO(4,"userNameFour", "PassWordCorrect-4", "fullNameFour", "USER");
        // WHEN
        Mockito.when(userRepository.findById(4)).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> userService.updateUser(userDTO,4));
    }

    //------------DeleteUser--------------------------------------------------------------------------------------------------------------
    @Test
    public void deleteUserExistTest(){
        // GIVEN
        User userAfterFindById = new User(4,"userNameFour", "PassWordCorrect-4", "fullNameFour", "USER");
        // WHEN
        Mockito.when(userRepository.findById(4)).thenReturn(java.util.Optional.of(userAfterFindById));
        // THEN
        userService.deleteUser(4);
        verify(userRepository, times(1)).deleteById(any(Integer.class));
    }

    @Test
    public void deleteUserNoExistTest(){
        // WHEN
        Mockito.when(userRepository.findById(any(int.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> userService.deleteUser(any(Integer.class)));
    }
    //------------GetUserById-------------------------------------------------------------------------------------------------------------
    @Test
    public void getUserExistTest(){
        // GIVEN
        User userAfterFindById = new User(4,"userNameFour", "PassWordCorrect-4", "fullNameFour", "USER");
        UserDTO userDTO = new UserDTO(4,"userNameFour", "PassWordCorrect-4", "fullNameFour", "USER");
        // WHEN
        Mockito.when(userRepository.findById(any(int.class))).thenReturn(java.util.Optional.of(userAfterFindById));
        Mockito.when(dtoBuilder.buildUserDTO(any(User.class))).thenReturn(userDTO);
        // THEN
        assertEquals(userService.getUserById(4), userDTO);

    }

    @Test
    public void getUserNoExistTest(){
        // WHEN
        Mockito.when(userRepository.findById(any(int.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> userService.getUserById(any(int.class)));
    }
    //------------------------------------------------------------------------------------------------------------------------------------
}
