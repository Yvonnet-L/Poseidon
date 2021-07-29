package com.ly.p7.Poseidon.unitaryTest.service;

import com.ly.p7.Poseidon.repositories.BidListRepository;
import com.ly.p7.Poseidon.service.implentation.BidListService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {

    @InjectMocks
    BidListService bidListService;

    @Mock
    BidListRepository bidListRepository;

    
}
