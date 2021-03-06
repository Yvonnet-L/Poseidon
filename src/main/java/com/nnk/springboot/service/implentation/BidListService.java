package com.nnk.springboot.service.implentation;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.interfaces.IBidListService;
import com.nnk.springboot.tool.DtoBuilder;
import com.nnk.springboot.tool.ModelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  BidListService
 *
 * @See BidList
 * @See BidListDTO
 * @See DtoBuilder
 * @See ModelBuilder
 */
@Service
public class BidListService implements IBidListService {

    @Autowired
    BidListRepository bidListRepository;

    @Autowired
    DtoBuilder dtoBuilder;

    @Autowired
    ModelBuilder modelBuilder;

    private static Logger logger = LogManager.getLogger(BidListService.class);
    //------------getAllBidList-------------------------------------------------------------------------------------
    /**
     * Method to List All BidList
     *
     * @return List<BidListDTO> bidListDTOList
     */
    @Override
    public List<BidListDTO> getAllBidList() {
        logger.info(" ---> Launch getAllBidList");
        List<BidListDTO> bidListDTOList = new ArrayList<>();
        List<BidList> bidListList = bidListRepository.findAll();

        for(BidList bidList: bidListList){
            bidListDTOList.add(dtoBuilder.buildUBidListDTO(bidList));
        }
        return bidListDTOList;
    }
    //------------addBidList-------------------------------------------------------------------------------------
    /**
     * Method to add a BidList
     *
     * @Param  BidListDTO bidListDTO
     * @return BidListDTO validated return of DB
     */
    @Override
    public BidListDTO addBidList(BidListDTO bidListDTO) {
        logger.info(" ---> Launch addBidList");
        BidList bidList = bidListRepository.save(modelBuilder.buildBidList(bidListDTO));

        return dtoBuilder.buildUBidListDTO(bidList);
    }

    //------------updateBidList-------------------------------------------------------------------------------------
    /**
     * Method to update a BidList
     *
     * @Param  BidListDTO bidListDTO
     * @Param  int idBidList
     * @return BidListDTO validated return of DB
     */
    @Override
    public BidListDTO updateBidList(BidListDTO bidListDTO, int idBidList) {
        logger.info(" ---> updateBidList with id: " + idBidList);
        BidList bidListVerif = bidListRepository.findById(idBidList).orElseThrow(()
                -> new DataNotFoundException("BidList with id=" + idBidList + " not found in DataBase"));

        BidList bidList = modelBuilder.buildBidList(bidListDTO);
        bidList.setBidListId(idBidList);
        bidList = bidListRepository.save(bidList);
        return dtoBuilder.buildUBidListDTO(bidList);
    }
    //------------deleteBidList----------------------------------------------------------------------------------
    /**
     * Void to delete BidList with id
     *
     * @Param  int id of bidList
     */
    @Override
    public void deleteBidList(int idBidList) {
        logger.info(" ---> deleteBidList with id: " + idBidList);
        BidList bidListVerif = bidListRepository.findById(idBidList).orElseThrow(()
                -> new DataNotFoundException("BidList with id=" + idBidList + " not found in DataBase"));
        bidListRepository.deleteById(idBidList);
        logger.info("  ---> ** deleted ** BidList with id: " + idBidList);
    }
    //------------getBidListById-------------------------------------------------------------------------------------
    /**
     * Method to get a BidList with id
     *
     * @Param  int idBidList
     * @return BidListDTO validated return of DB
     */
    @Override
    public BidListDTO getBidListById(int idBidList) {
        logger.info(" ---> getBidListById with id: " + idBidList);
        BidList bidList = bidListRepository.findById(idBidList).orElseThrow(()
                -> new DataNotFoundException("BidList with id=" + idBidList + " not found in DataBase"));
        logger.info("  ---> ** Find ** BidList with id: " + idBidList );
        return dtoBuilder.buildUBidListDTO(bidList);
    }
    //--------------------------------------------------------------------------------------------------------
}
