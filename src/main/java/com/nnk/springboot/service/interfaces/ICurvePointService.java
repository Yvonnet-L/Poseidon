package com.nnk.springboot.service.interfaces;

import com.nnk.springboot.dto.CurvePointDTO;

import java.util.List;

public interface ICurvePointService {

    List<CurvePointDTO> getAllCurvePoint();

    CurvePointDTO addCurvePoint(CurvePointDTO curvePointDTO);

    CurvePointDTO updateCurvePoint(CurvePointDTO curvePointDTO, int id);

    void deleteCurvePoint(int id);

    CurvePointDTO getCurvePointById(int id);

}
