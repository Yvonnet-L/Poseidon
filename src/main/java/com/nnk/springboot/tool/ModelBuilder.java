package com.nnk.springboot.tool;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * This class allows the construction of a Model from a DTO
 */

@Component
public class ModelBuilder {

    private static Logger logger = LogManager.getLogger(DtoBuilder.class);

    //--------------build-User-----------------------------------------------------------------------------
    /**
     *  Build User with UserDTO
     *
     * @param  userDTO UserDTO
     * @return user User
     */

    public User buildUser(final UserDTO userDTO) {
        logger.info( " ---> Launch buildUser");
        return new User(userDTO.getUsername(),  userDTO.getFullname(),
                             userDTO.getPassword(),userDTO.getRole());
    }

    //--------------build-BidList---------------------------------------------------------------------------
    /**
     *  Build bidList with bidListDTO
     *
     * @param  bidListDTO BidListDTO
     * @return user User
     */

    public BidList buildBidList(final BidListDTO bidListDTO) {
        logger.info( " ---> Launch buildBidList");

        return new BidList(bidListDTO.getAccount(), bidListDTO.getType(),
                            bidListDTO.getBidQuantity());
    }
    //-------------------------------------------------------------------------------------------
}