package com.nnk.springboot.unitaryTest.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.implentation.CurvePointService;
import com.nnk.springboot.tool.DtoBuilder;
import com.nnk.springboot.tool.ModelBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {

    @InjectMocks
    CurvePointService curvePointService;

    @Mock
    CurvePointRepository curvePointRepository;

    @Mock
    DtoBuilder dtoBuilder;

    @Mock
    ModelBuilder modelBuilder;

    private List<CurvePoint> curvePointList;

    //---------- GetAllCurvePoint-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur getAllCurvePoint")
    public void getAllCurvePointTest(){
        // GIVEN
        CurvePoint curvePoint1 = new CurvePoint( 1, 1, 11.11, 11.11);
        CurvePoint curvePoint2 = new CurvePoint(2,2,2.22,2.22);
        CurvePoint curvePoint3 = new CurvePoint(3,3,3.33,3.33);
        curvePointList = Arrays.asList(curvePoint1, curvePoint2, curvePoint3);
        // WHEN
        Mockito.when(curvePointRepository.findAll()).thenReturn(curvePointList);
        // THEN
        assertThat(curvePointService.getAllCurvePoint().size()).isEqualTo(3);
    }

    //---------- AddCurvePoint-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur addCurvePoint")
    public void addCurvePointTest(){
        // GIVEN
        CurvePointDTO curvePointDTOofView = new CurvePointDTO();
        // WHEN

        // THEN

    }
    //---------- DeleteCurvePoint-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur deleteeCurvePoint")
    public void deleteCurvePointTest(){
        // GIVEN

        // WHEN

        // THEN

    }
    //---------- GetCurvePointById-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur getAllCurvePoint")
    public void getCurvePointByIdTest(){
        // GIVEN

        // WHEN

        // THEN

    }
}
