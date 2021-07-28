package com.ly.p7.Poseidon.service.interfaces;


import com.ly.p7.Poseidon.dto.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAllUser();

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, int idUser);

    void deleteUser(int idUser);

    UserDTO getUserById(int id);


}
