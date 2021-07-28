package com.ly.p7.Poseidon.service.interfaces;

import com.ly.p7.Poseidon.dto.BidListDTO;

import java.util.List;

public interface IBidListService {


    List<BidListDTO> getAllBidList();

    BidListDTO addBidList(BidListDTO bidListDTO);

    BidListDTO updateUser(BidListDTO bidListDTO, int idBidList);

    void deleteBidList(int id);

    BidListDTO getBidListById(int id);
}
