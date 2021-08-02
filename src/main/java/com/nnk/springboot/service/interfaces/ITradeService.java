package com.nnk.springboot.service.interfaces;

import com.nnk.springboot.dto.TradeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITradeService  {

    List<TradeDTO> getAllTrade();

    TradeDTO addTrade(TradeDTO tradeDTO);

    TradeDTO updateTrade(TradeDTO tradeDTO, int id);

    void deleteTrade(int id);

    TradeDTO getTradeById(int id);

}
