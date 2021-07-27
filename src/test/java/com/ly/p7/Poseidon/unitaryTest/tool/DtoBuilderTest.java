package com.ly.p7.Poseidon.unitaryTest.tool;

import com.ly.p7.Poseidon.domain.User;
import com.ly.p7.Poseidon.dto.UserDTO;
import com.ly.p7.Poseidon.tool.DtoBuilder;
import com.ly.p7.Poseidon.tool.ModelBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DtoBuilderTest {


    @InjectMocks
    DtoBuilder dtolBuilder;

    @Test
    public void builderUserTest(){
        User user = new User("userName", "PassWordCorrect-1", "fullName", "USER");
        UserDTO userDTO = new UserDTO("userName", "PassWordCorrect-1", "fullName", "USER");

        assertThat((dtolBuilder.buildUserDTO(user)).equals(userDTO));

    }
}
