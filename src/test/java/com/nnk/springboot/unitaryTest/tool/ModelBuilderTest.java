package com.nnk.springboot.unitaryTest.tool;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.tool.ModelBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ModelBuilderTest {

    @InjectMocks
    ModelBuilder modelBuilder;

    //-------------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("User Builder Test")
    public void builderUserTest(){
        User user = new User("userName", "PassWordCorrect-1", "fullName", "USER");
        UserDTO userDTO = new UserDTO("userName", "PassWordCorrect-1", "fullName", "USER");

        assertThat((modelBuilder.buildUser(userDTO)).equals(user));

    }
    //-------------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("BidList Builder Test")
    public void builderBidListTest(){
        BidList bidList = new BidList("account1", "Type1", 45.50);
        BidListDTO bidListDTO = new BidListDTO("account1", "Type1", 45.50);

        assertThat((modelBuilder.buildBidList(bidListDTO)).equals(bidList));

    }
    //-------------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("CurvePoint Builder Test")
    public void builderCurvePointTest(){
        CurvePoint curvePoint= new CurvePoint(2, 12.56, 45.50);
        CurvePointDTO curvePointDTO = new CurvePointDTO(2, 12.56, 45.50);

        assertThat((modelBuilder.buildCurvePoint(curvePointDTO)).equals(curvePoint));

    }
    //-----------------Trade--------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Trade Builder Test")
    public void buildTradeTest(){
        Trade trade= new Trade("account1", "type1", 11.11);
        TradeDTO tradeDTO= new TradeDTO("account1", "type1", 11.11);

        Assertions.assertThat((modelBuilder.buildTrade(tradeDTO)).equals(trade));
    }
}
