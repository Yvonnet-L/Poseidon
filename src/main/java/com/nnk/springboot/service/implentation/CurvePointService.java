package com.nnk.springboot.service.implentation;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.interfaces.ICurvePointService;
import com.nnk.springboot.tool.DtoBuilder;
import com.nnk.springboot.tool.ModelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  CurvePointServiceService
 *
 * @See CurvePoint
 * @See CurvePointtDTO
 * @See DtoBuilder
 * @See ModelBuilder
 */

@Service
public class CurvePointService implements ICurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    @Autowired
    DtoBuilder dtoBuilder;

    @Autowired
    ModelBuilder modelBuilder;

    private static Logger logger = LogManager.getLogger(CurvePointService.class);

    //------------getAllCurvePoint-------------------------------------------------------------------------------------
    /**
     * Method to List All CurvePoint
     *
     * @return List<CurvePointDTO> curvePointDTOList
     */

    @Override
    public List<CurvePointDTO> getAllCurvePoint() {
        logger.info(" ---> Launch getAllCurvePoint");
        List<CurvePointDTO> curvePointDTOList = new ArrayList<>();
        List<CurvePoint> curvePointList = curvePointRepository.findAll();

        for(CurvePoint curvePoint: curvePointList){
            curvePointDTOList.add(dtoBuilder.buildCurvePointDTO(curvePoint));
        }
        return curvePointDTOList;
    }
    //------------addCurvePoint-------------------------------------------------------------------------------------
    /**
     * Method to add a CurvePoint
     *
     * @Param  CurvePointDTO curvePointDTO
     * @return CurvePointtDTO validated return of DB
     */
    @Override
    public CurvePointDTO addCurvePoint(CurvePointDTO curvePointDTO) {
        logger.info(" ---> Launch addCurvePoint");
        CurvePoint curvePoint = curvePointRepository.save(modelBuilder.buildCurvePoint(curvePointDTO));
        return dtoBuilder.buildCurvePointDTO(curvePoint);
    }
    //------------updateCurvePoint-------------------------------------------------------------------------------------
    /**
     * Method to update a CurvePoint
     *
     * @Param  CurvePointDTO curvePointDTO
     * @Param  int id ( id of CurvePoint )
     * @return CurvePointDTO validated return of DB
     */
    @Override
    public CurvePointDTO updateCurvePoint(CurvePointDTO curvePointDTO, int id) {
        logger.info(" ---> Launch updateCurvePoint");
        CurvePoint curvePointVerif = curvePointRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException("CurvePoint with id=" + id + " not found in DataBase"));
        CurvePoint curvePoint = modelBuilder.buildCurvePoint(curvePointDTO);
        // ajout de l'id
        curvePoint.setCurveId(id);
        curvePoint = curvePointRepository.save(curvePoint);
        return dtoBuilder.buildCurvePointDTO(curvePoint);
    }
    //------------deleteCurvePoint----------------------------------------------------------------------------------
    /**
     * Void to delete CurvePoint with id
     *
     * @Param  int id of CurvePoint
     */
    @Override
    public void deleteCurvePoint(int id) {
        logger.info(" ---> Launch deleteCurvePoint");
        CurvePoint curvePointVerif = curvePointRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException("CurvePoint with id=" + id + " not found in DataBase"));
        curvePointRepository.deleteById(id);
        logger.info("  ---> ** deleted ** CurvePoint with id: " + id);
    }
    //------------getCurvePointById-------------------------------------------------------------------------------------
    /**
     * Method to get a CurvePoint with id
     *
     * @Param  int id
     * @return CurvePointDTO validated return of DB
     */
    @Override
    public CurvePointDTO getCurvePointById(int id) {
        logger.info(" ---> Launch getCurvePointById");
        CurvePoint curvePointfind = curvePointRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException("CurvePoint with id=" + id + " not found in DataBase"));
        logger.info("  ---> ** find ** CurvePoint with id: " + id);
        return dtoBuilder.buildCurvePointDTO(curvePointfind);
    }
}
