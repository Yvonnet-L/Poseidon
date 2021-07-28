package com.ly.p7.Poseidon.tool;


import com.ly.p7.Poseidon.domain.BidList;
import com.ly.p7.Poseidon.domain.User;
import com.ly.p7.Poseidon.dto.BidListDTO;
import com.ly.p7.Poseidon.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
  * This class allows the construction of a DTO from a Model
 */

@Component
public class DtoBuilder {

private static Logger logger = LogManager.getLogger(DtoBuilder.class);

    //-----------------UserDTO --------------------------------------------------------------------------
     /**
      *  Build UserDTO with User
      *
      * @param user User
      * @return userDto UserDTO
      */

     public UserDTO buildUserDTO(final User user) {

         return new UserDTO(user.getId(),user.getUsername(),
                            user.getPassword(),user.getFullname(), user.getRole());
     }
     //-----------------BidListDTO-----------------------------------------------------------------------
    /**
     *  Build BidListDTO with BidList
     *
     * @param bidList BidList
     * @return bidListDTO BidListDTO
     */

    public BidListDTO buildUBidListDTO(final BidList bidList) {

        return new BidListDTO(bidList.getBidListId(), bidList.getAccount(),
                                bidList.getType(), bidList.getBidQuantity());
    }
    //-------------------------------------------------------------------------------------------
}
