package com.nnk.springboot.unitaryTest.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.exceptions.DataNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

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
        CurvePoint curvePoint1 = new CurvePoint(1,1,11.11, 1.11);
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
        CurvePointDTO curvePointDTOofView = new CurvePointDTO(4,4.44,44.44);
        CurvePoint curvePointBuild = new CurvePoint(4,4.44,44.44);
        CurvePoint curvePointResultSave = new CurvePoint(4,4,4.44,44.44);
        CurvePointDTO curvePointDTOResult = new CurvePointDTO(4,4,4.44,44.44);
        // WHEN
        Mockito.when(modelBuilder.buildCurvePoint(curvePointDTOofView)).thenReturn(curvePointBuild);
        Mockito.when(curvePointRepository.save(curvePointBuild)).thenReturn(curvePointResultSave);
        Mockito.when(dtoBuilder.buildCurvePointDTO(curvePointResultSave)).thenReturn(curvePointDTOResult);
        // THEN
        assertThat(curvePointService.addCurvePoint(curvePointDTOofView)).isEqualTo(curvePointDTOResult);

    }
    //---------- UpdateCurvePoint-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur deleteCurvePoint with curvePoint Exist")
    public void updateCurvePointTestWithCurvePointExist(){
        // GIVEN
        CurvePointDTO curvePointDTOofView = new CurvePointDTO(4,4.44,44.44);
        CurvePoint curvePointBuild = new CurvePoint(4,4.44,44.44);
        CurvePoint curvePointFind = new CurvePoint(4,4,2.22,3.33);
        CurvePoint curvePointResultSave = new CurvePoint(4,4,4.44,44.44);
        CurvePointDTO curvePointDTOResult = new CurvePointDTO(4,4,4.44,44.44);
        // WHEN
        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(curvePointFind));
        Mockito.when(modelBuilder.buildCurvePoint(curvePointDTOofView)).thenReturn(curvePointBuild);
        Mockito.when(curvePointRepository.save(curvePointBuild)).thenReturn(curvePointResultSave);
        Mockito.when(dtoBuilder.buildCurvePointDTO(curvePointResultSave)).thenReturn(curvePointDTOResult);
        // THEN
        assertThat(curvePointService.updateCurvePoint(curvePointDTOofView, 4)).isEqualTo(curvePointDTOResult);
    }

    @Test
    @DisplayName("Test sur updateCurvePoint with curvePoint not Exist")
    public void updateCurvePointTestWithCurvePointNotExist(){
        // GIVEN
        CurvePointDTO curvePointDTOofView = new CurvePointDTO(4,4.44,44.44);
        // WHEN
        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> curvePointService.updateCurvePoint(curvePointDTOofView,any(Integer.class)));

    }
    //---------- DeleteCurvePointById-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur deleteCurvePoint")
    public void deleteCurvePointByIdExistTest(){
        // GIVEN
        CurvePoint curvePointFind = new CurvePoint(4,4,2.22,3.33);
        // WHEN
        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(curvePointFind));
        // THEN
        curvePointService.deleteCurvePoint(any(Integer.class));
    }
    @Test
    @DisplayName("Test sur deleteCurvePoint")
    public void deleteCurvePointByIdNotExistTest(){
        // WHEN
        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> curvePointService.deleteCurvePoint(any(Integer.class)));
    }
    //---------- GetCurvePointById-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur getCurvePointById with id exist")
    public void getCurvePointByIdExistTest(){
        // GIVEN
        CurvePoint curvePointFind = new CurvePoint(4,4,2.22,3.33);
        CurvePointDTO curvePointDTOResult = new CurvePointDTO(4,4,2.22,3.33);
        // WHEN
        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(curvePointFind));
        Mockito.when(dtoBuilder.buildCurvePointDTO(any(CurvePoint.class))).thenReturn(curvePointDTOResult);
        // THEN
        assertThat(curvePointService.getCurvePointById(any(Integer.class))).isEqualTo(curvePointDTOResult);
    }

    @Test
    @DisplayName("Test sur getCurvePointById with id not exist")
    public void getCurvePointByIdNotExistTest(){
        // WHEN
        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> curvePointService.getCurvePointById(any(Integer.class)));
    }

}
