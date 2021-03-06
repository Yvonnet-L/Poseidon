package com.nnk.springboot.tool;


import com.nnk.springboot.domain.*;
import com.nnk.springboot.dto.*;
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
         logger.info( " ---> Launch buildUserDTO");
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
        logger.info( " ---> Launch buildUBidListDTO");
        return new BidListDTO(bidList.getBidListId(), bidList.getAccount(),
                                bidList.getType(), bidList.getBidQuantity());
    }
    //-----------------CurvePointDTO-----------------------------------------------------------------------
    /**
     *  Build CurvePointDTO with CurvePoint
     *
     * @param curvePoint CurvePoint
     * @return curvePointDTO CurvePointDTO
     */

    public CurvePointDTO buildCurvePointDTO(final CurvePoint curvePoint) {
        logger.info( " ---> Launch buildCurvePointDTO");
        return new CurvePointDTO(curvePoint.getId(), curvePoint.getCurveId(),
                curvePoint.getTerm(), curvePoint.getValue());
    }
    //-----------------TradeDTO-----------------------------------------------------------------------
    /**
     *  Build CurvePointDTO with CurvePoint
     *
     * @param trade Trade
     * @return tradeDTO TradeDTO
     */
    public TradeDTO buildTradeDTO(final Trade trade) {
        logger.info( " ---> Launch buildTradeDTO");
        return new TradeDTO(trade.getTradeId(), trade.getAccount(),
                                trade.getType(), trade.getBuyQuantity());
    }
    //-----------------RatingDTO-----------------------------------------------------------------------
    /**
     *  Build RatingDTO with Rating
     *
     * @param rating Rating
     * @return ratingDTO RatingDTO
     */
    public RatingDTO buildRatingDTO(final Rating rating) {
        logger.info( " ---> Launch buildRatingDTO");
        return new RatingDTO(rating.getId(), rating.getMoodysRating(),rating.getSandRating(),
                             rating.getFitchRating(), rating.getOrderNumber() );
    }
    //-----------------RuleNameDTO-----------------------------------------------------------------------
    /**
     *  Build RuleNameDTO with RuleName
     *
     * @param ruleName RuleName
     * @return ruleNameDTO RuleNameDTO
     */
    public RuleNameDTO buildRuleNameDTO(final RuleName ruleName) {
        logger.info( " ---> Launch buildRuleNameDTO");
        return new RuleNameDTO(ruleName.getId(), ruleName.getName(), ruleName.getDescription(),
                                ruleName.getJson(), ruleName.getTemplate(), ruleName.getSqlStr(),
                                    ruleName.getSqlPart());
    }
    //-------------------------------------------------------------------------------------------

}
