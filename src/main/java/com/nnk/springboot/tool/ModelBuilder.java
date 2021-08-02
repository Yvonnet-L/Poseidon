package com.nnk.springboot.tool;


import com.nnk.springboot.domain.*;
import com.nnk.springboot.dto.*;
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
    //--------------build-CurvePoint---------------------------------------------------------------------------
    /**
     *  Build CurvePoint with CurvePointDTO
     *
     * @param  curvePointDTO CurvePointDTO
     * @return curvePoint CurvePoint
     */

    public CurvePoint buildCurvePoint(final CurvePointDTO curvePointDTO) {
        logger.info( " ---> Launch buildCurvePoint");

        return new CurvePoint(curvePointDTO.getCurveId(), curvePointDTO.getTerm(),
                curvePointDTO.getValue());
    }
    //--------------build-Trade---------------------------------------------------------------------------
    /**
     *  Build Trade with TradeDTO
     *
     * @param  tradeDTO TradeDTO
     * @return trade Trade
     */
    public Trade buildTrade(final TradeDTO tradeDTO) {
        logger.info( " ---> Launch buildTrade");

        return new Trade(tradeDTO.getAccount(),tradeDTO.getType(), tradeDTO.getBuyQuantity());
    }
    //--------------build-Rating---------------------------------------------------------------------------
    /**
     *  Build CurvePoint with CurvePointDTO
     *
     * @param  ratingDTO RatingDTO
     * @return rating Rating
     */
    public Rating buildRating(final RatingDTO ratingDTO) {
        logger.info( " ---> Launch buildTrade");
        return new Rating(ratingDTO.getMoodyRating(),ratingDTO.getSandRating(), ratingDTO.getFitchRating(),
                       ratingDTO.getOrderNumber() );
    }
    //-------------------------------------------------------------------------------------------

}
