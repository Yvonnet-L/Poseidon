package com.nnk.springboot.service.implentation;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.DataAlreadyExistException;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.interfaces.IUserService;
import com.nnk.springboot.tool.DtoBuilder;
import com.nnk.springboot.tool.ModelBuilder;
import net.bytebuddy.implementation.bytecode.Throw;
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

    private static Logger logger = LogManager.getLogger(UserService.class);

    //----------------getAllUser-------------------------------------------------------------------------------
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
     //----------------addUser----------------------------------------------------------------------------------
     /**
      * @Param  UserDTO userDTO
      * @return UserDTO validated return of DB
      */
     @Override
     public UserDTO addUser(UserDTO userDTO) {
         logger.info(" ---> Launch addlUser");
         if( userRepository.findUserByUsername(userDTO.getUsername()).isPresent()){
             throw new DataAlreadyExistException("user with username=" + userDTO.getUsername() + " already exist !");
         }
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         userDTO.setPassword(encoder.encode(userDTO.getPassword()));
         logger.info(" ---> Launch addlUser :" + userDTO.getFullname() + " -- " + userDTO.getUsername() + " -- " +
                          userDTO.getPassword() + " -- " + userDTO.getRole()+ " -- " );
         User userAdd = userRepository.save(modelBuilder.buildUser(userDTO));

         return dtoBuilder.buildUserDTO(userAdd);
     }
     //----------------updateUser-------------------------------------------------------------------------------
     /**
      * @Param idUser
      * @Param userDTO
      * @return userAdd
      */
     @Override
     public UserDTO updateUser(UserDTO userDTO, int idUser) {
         logger.info(" ---> Launch updateUser");

         User user = userRepository.findById(idUser).orElseThrow(()
                 -> new DataNotFoundException("user with id=" + idUser + " not found in DataBase"));
         // --  password encoding --
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         userDTO.setPassword(encoder.encode(userDTO.getPassword()));
         // -- built user before save --
         User userAdd = modelBuilder.buildUser(userDTO);
         userAdd.setId(idUser);
         userAdd = userRepository.save(userAdd);

         return dtoBuilder.buildUserDTO(userAdd);
     }
     //-----------------deleteUser------------------------------------------------------------------------------
     /**
      * @param idUser
      */
     @Override
     public void deleteUser(int idUser) {
         logger.info(" ---> Launch addlUser");
         // - Verication user exist -
         User user = userRepository.findById(idUser).orElseThrow(()
                 -> new DataNotFoundException("user with id=" + idUser + " not found in DataBase"));
         //- Launch delete -
         userRepository.deleteById(idUser);
     }
     //-----------------getUserById-----------------------------------------------------------------------------
     /**
      * Method which allows to retrieve a user by its id, if not found return a DataNotFoundException
      *
      * @param id ( idUser )
      * @Return UserDTO
      */
     @Override
     public UserDTO getUserById(int id) {
         logger.info(" ---> getUserById");
         User user = userRepository.findById(id).orElseThrow(()
                         -> new DataNotFoundException("user with id=" + id + " not found in DataBase"));
         // reset password -  original code action -
         user.setPassword("");
         return dtoBuilder.buildUserDTO(user);
     }
     //-------------------------------------------------------------------------------------------------


}
