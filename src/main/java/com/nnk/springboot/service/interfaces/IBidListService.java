package com.nnk.springboot.service.interfaces;

import com.nnk.springboot.dto.BidListDTO;

import java.util.List;

public interface IBidListService {


    List<BidListDTO> getAllBidList();

    BidListDTO addBidList(BidListDTO bidListDTO);

    BidListDTO updateBidList(BidListDTO bidListDTO, int idBidList);

    void deleteBidList(int id);

    BidListDTO getBidListById(int id);
}
