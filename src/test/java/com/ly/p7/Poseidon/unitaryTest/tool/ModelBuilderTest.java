package com.ly.p7.Poseidon.unitaryTest.tool;

import com.ly.p7.Poseidon.domain.BidList;
import com.ly.p7.Poseidon.domain.User;
import com.ly.p7.Poseidon.dto.BidListDTO;
import com.ly.p7.Poseidon.dto.UserDTO;
import com.ly.p7.Poseidon.tool.ModelBuilder;
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
}
