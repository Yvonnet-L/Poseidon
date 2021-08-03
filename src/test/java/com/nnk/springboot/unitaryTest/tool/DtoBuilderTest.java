package com.nnk.springboot.unitaryTest.tool;

import com.nnk.springboot.domain.*;
import com.nnk.springboot.dto.*;
import com.nnk.springboot.tool.DtoBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DtoBuilderTest {


    @InjectMocks
    DtoBuilder dtolBuilder;

    //-----------------UserDTO--------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("UserDTO Builder Test")
    public void builderUserTest(){
        User user = new User(8,"userName", "PassWordCorrect-1", "fullName", "USER");
        UserDTO userDTO = new UserDTO(8,"userName", "PassWordCorrect-1", "fullName", "USER");

        assertThat((dtolBuilder.buildUserDTO(user)).equals(userDTO));
    }
    //-----------------BidListDTO --------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("BidListDTO Builder Test")
    public void buildBidListTest(){
        BidList bidList = new BidList(1,"account1", "Type1", 45.50);
        BidListDTO bidListDTO = new BidListDTO(1,"account1", "Type1", 45.50);

        Assertions.assertThat((dtolBuilder.buildUBidListDTO(bidList)).equals(bidListDTO));
    }
    //-----------------CurvePointDTO------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("CurvePointDTO Builder Test")
    public void buildCurvPointTest(){
        CurvePoint curvePoint= new CurvePoint(1,2, 12.56, 45.50);
        CurvePointDTO curvePointDTO = new CurvePointDTO(1,2, 12.56, 45.50);

        Assertions.assertThat((dtolBuilder.buildCurvePointDTO(curvePoint)).equals(curvePointDTO));
    }
    //-----------------TradeDTO--------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("TradetDTO Builder Test")
    public void buildTradeTest(){
        Trade trade= new Trade(1,"account1", "type1", 11.11);
        TradeDTO tradeDTO= new TradeDTO(1,"account1", "type1", 11.11);

        Assertions.assertThat((dtolBuilder.buildTradeDTO(trade)).equals(tradeDTO));
    }
    //-----------------RatingDTO--------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("RatingDTO Builder Test")
    public void buildRatingTest(){
        Rating rating= new Rating(1,"moody1","sand1", "fitch1", 10);
        RatingDTO ratingDTO= new RatingDTO(1,"moody1","sand1", "fitch1", 10);

        Assertions.assertThat((dtolBuilder.buildRatingDTO(rating)).equals(ratingDTO));
    }
    //-----------------RuleNameDTO--------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("RuleNameDTO Builder Test")
    public void buildRuleNameTest(){
        RuleName ruleName= new RuleName(1,"name1","descript1", "json1",
                                            "template1", "sqlStr1","sqlPart1");
        RuleNameDTO ruleNameDTO= new RuleNameDTO(1,"name1","descript1", "json1",
                                            "template1", "sqlStr1","sqlPart1");
        Assertions.assertThat((dtolBuilder.buildRuleNameDTO(ruleName)).equals(ruleNameDTO));
    }
}
