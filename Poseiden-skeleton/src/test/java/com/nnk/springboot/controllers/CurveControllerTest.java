package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CurveControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CurvePointService curvePointService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private CurveController curveController;

    private CurvePoint curvePoint;

    @BeforeEach
    void setUp() {
        curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(10);
        curvePoint.setTerm(2.5);
        curvePoint.setValue(100.0);

        mockMvc = MockMvcBuilders.standaloneSetup(curveController).build();
    }

    @Test
    void testHome_ShouldReturnCurvePointListPage() throws Exception {
        List<CurvePoint> curvePoints = Arrays.asList(curvePoint);
        when(curvePointService.getAllCurvePoints()).thenReturn(curvePoints);

        String viewName = curveController.home(model);

        assertEquals("curvePoint/list", viewName);
        verify(curvePointService, times(1)).getAllCurvePoints();
        verify(model, times(1)).addAttribute("curvePoints", curvePoints);
    }

    @Test
    void testAddCurveForm_ShouldReturnAddPage() {
        String viewName = curveController.addCurveForm(model);

        assertEquals("curvePoint/add", viewName);
        verify(model, times(1)).addAttribute(eq("curvePoint"), any(CurvePoint.class));
    }

    @Test
    void testValidate_ShouldSaveCurvePointAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = curveController.validate(curvePoint, bindingResult, model);

        assertEquals("redirect:/curvePoint/list", viewName);
        verify(curvePointService, times(1)).saveCurvePoint(curvePoint);
    }

    @Test
    void testValidate_ShouldReturnAddPageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = curveController.validate(curvePoint, bindingResult, model);

        assertEquals("curvePoint/add", viewName);
        verify(curvePointService, times(0)).saveCurvePoint(any(CurvePoint.class));
    }

    @Test
    void testShowUpdateForm_ShouldReturnUpdatePage() {
        when(curvePointService.getCurvePointById(1)).thenReturn(curvePoint);

        String viewName = curveController.showUpdateForm(1, model);

        assertEquals("curvePoint/update", viewName);
        verify(model, times(1)).addAttribute("curvePoint", curvePoint);
    }

    @Test
    void testUpdateCurve_ShouldUpdateCurvePointAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = curveController.updateCurve(1, curvePoint, bindingResult, model);

        assertEquals("redirect:/curvePoint/list", viewName);
        verify(curvePointService, times(1)).updateCurvePoint(1, curvePoint);
    }

    @Test
    void testUpdateCurve_ShouldReturnUpdatePageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = curveController.updateCurve(1, curvePoint, bindingResult, model);

        assertEquals("curvePoint/update", viewName);
        verify(curvePointService, times(0)).updateCurvePoint(anyInt(), any(CurvePoint.class));
    }

    @Test
    void testDeleteCurve_ShouldDeleteCurvePointAndRedirect() {
        String viewName = curveController.deleteCurve(1, model);

        assertEquals("redirect:/curvePoint/list", viewName);
        verify(curvePointService, times(1)).deleteCurvePoint(1);
    }
}
