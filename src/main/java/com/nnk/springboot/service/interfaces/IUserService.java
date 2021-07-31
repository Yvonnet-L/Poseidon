package com.nnk.springboot.service.interfaces;


import com.nnk.springboot.dto.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAllUser();

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, int idUser);

    void deleteUser(int idUser);

    UserDTO getUserById(int id);


}
