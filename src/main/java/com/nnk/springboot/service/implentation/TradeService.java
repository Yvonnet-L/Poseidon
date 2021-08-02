package com.nnk.springboot.service.implentation;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.interfaces.ITradeService;
import com.nnk.springboot.tool.DtoBuilder;
import com.nnk.springboot.tool.ModelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  TradeService
 *
 * @See Trade
 * @See TradeDTO
 * @See DtoBuilder
 * @See ModelBuilder
 */
@Service
public class TradeService implements ITradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    DtoBuilder dtoBuilder;

    @Autowired
    ModelBuilder modelBuilder;

    private static Logger logger = LogManager.getLogger(TradeService.class);

    //------------getAllTrade-------------------------------------------------------------------------------------
    /**
     * Method to List All Trade
     *
     * @return List<TradeDTO> tradeDTOList
     */
    @Override
    public List<TradeDTO> getAllTrade() {
        logger.info(" ---> Launch getAllCurvePoint");
        List<TradeDTO> tradeDTOList = new ArrayList<>();
        List<Trade> tradeList = tradeRepository.findAll();

        for(Trade trade: tradeList){
            tradeDTOList.add(dtoBuilder.buildTradeDTO(trade));
        }
        return tradeDTOList;
    }
    //------------addTrade-------------------------------------------------------------------------------------
    /**
     * Method to add a Trade
     *
     * @Param  TradeDTO tradeDTO
     * @return TradeDTO validated return of DB
     */
    @Override
    public TradeDTO addTrade(TradeDTO tradeDTO) {
        logger.info(" ---> Launch addTrade");
        Trade trade = tradeRepository.save(modelBuilder.buildTrade(tradeDTO));
        return dtoBuilder.buildTradeDTO(trade);
    }
    //------------updateTrade-------------------------------------------------------------------------------------
    /**
     * Method to update a Trade
     *
     * @Param  TradeDTO tradeDTO
     * @Param  int id ( id of Trade )
     * @return TradeDTO validated return of DB
     */
    @Override
    public TradeDTO updateTrade(TradeDTO tradeDTO, int id) {
        logger.info(" ---> Launch updateTrade");
        Trade tradeFind = tradeRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException("Trade with id=" + id + " not found in DataBase"));
        Trade trade = modelBuilder.buildTrade(tradeDTO);
        trade.setTradeId(id);
        trade = tradeRepository.save(trade);
        return dtoBuilder.buildTradeDTO(trade);
    }
    //------------deleteTrade----------------------------------------------------------------------------------
    /**
     * Void to delete Trade with id
     *
     * @Param  int id of Trade
     */
    @Override
    public void deleteTrade(int id) {
        logger.info(" ---> Launch deleteTrade");
        Trade tradeFind = tradeRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException("Trade with id=" + id + " not found in DataBase"));
        tradeRepository.deleteById(id);
        logger.info("  ---> ** deleted ** Trade with id: " + id);
    }
    //------------getTradeById----------------------------------------------------------------------------------
    /**
     * Void to get Trade with id
     *
     * @Param  int id of Trade
     */
    @Override
    public TradeDTO getTradeById(int id) {
        logger.info(" ---> Launch getTradeById");
        Trade tradeFind = tradeRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException("Trade with id=" + id + " not found in DataBase"));
        logger.info("  ---> ** find ** Trade with id: " + id);
        return dtoBuilder.buildTradeDTO(tradeFind);
    }
}
