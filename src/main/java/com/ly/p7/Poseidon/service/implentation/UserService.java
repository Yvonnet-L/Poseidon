package com.ly.p7.Poseidon.service.implentation;

import com.ly.p7.Poseidon.conttoller.UserController;
import com.ly.p7.Poseidon.domain.User;
import com.ly.p7.Poseidon.dto.UserDTO;
import com.ly.p7.Poseidon.repositories.UserRepository;
import com.ly.p7.Poseidon.service.interfaces.IUserService;
import com.ly.p7.Poseidon.tool.DtoBuilder;
import com.ly.p7.Poseidon.tool.ModelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

 /**
 * userRepository.findAll()
 */
 @Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DtoBuilder dtoBuilder;

     @Autowired
     ModelBuilder modelBuilder;

    private static Logger logger = LogManager.getLogger(UserController.class);

    //-------------------------------------------------------------------------------------------------
     /**
      * @return List<UserDTO> userDTOList
      */
     @Override
     public List<UserDTO>  getAllUser() {
        logger.info(" ---> Launch getAllUser");

        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = userRepository.findAll();

        for (User user: userList){
            userDTOList.add(dtoBuilder.buildUserDTO(user));
        }

        return userDTOList;
     }
     //-------------------------------------------------------------------------------------------------
     /**
      * @Param  UserDTO userDTO
      * @return UserDTO validated return of DB
      */
     @Override
     public UserDTO addUser(UserDTO userDTO) {
         logger.info(" ---> Launch addlUser");

         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         userDTO.setPassword(encoder.encode(userDTO.getPassword()));
         logger.info(" ---> Launch addlUser :" + userDTO.getFullname() + " -- " + userDTO.getUsername() + " -- " +
                          userDTO.getPassword() + " -- " + userDTO.getRole()+ " -- " );
         User userAdd = userRepository.save(modelBuilder.buildUser(userDTO));

         return dtoBuilder.buildUserDTO(userAdd);
     }
     //-------------------------------------------------------------------------------------------------
     /**
      * @Param
      * @return
      */
     @Override
     public UserDTO updateUser(UserDTO userDTO, int idUser) {
         return null;
     }
     //-------------------------------------------------------------------------------------------------
     /**
      * @param
      */
     @Override
     public void deleteUser(int idUser) {

     }
     //-------------------------------------------------------------------------------------------------
}
