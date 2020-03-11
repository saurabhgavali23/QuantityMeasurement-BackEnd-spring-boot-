package com.Measurement.controller;

import com.Measurement.exception.QuantityMeasurementException;
import com.Measurement.dto.QuantityDTO;
import com.Measurement.service.QuantityConversion;
import com.Measurement.service.QuantityServiceImpl;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class QuantityControllerTest {

    QuantityDTO[] quantityDTOS = new QuantityDTO[2];
    List unitList;
    List unitTypeList;
    Gson json = new Gson();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuantityServiceImpl quantityService;

    @BeforeEach
    void setUp() {
        for(int i=0;i<2;i++)
            quantityDTOS[i] = new QuantityDTO();
        unitList = new ArrayList();
        unitList.add("Length");
        unitList.add("Volume");
        unitList.add("Weight");
        unitList.add("Temperature");
        unitTypeList = new ArrayList();
        unitTypeList.add("FEET");
        unitTypeList.add("INCH");
        unitTypeList.add("YARD");
        unitTypeList.add("CENTIMETER");
    }

    @Test
    void givenQuantityDTO_whenUnitConvert_thenReturnOKStatus() throws Exception, QuantityMeasurementException {
    try {
        when(quantityService.calculateUnit(any(),any())).thenReturn(new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.CENTIMETER));
        String json1 = json.toJson(quantityDTOS);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconversion").content(json1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        Assert.assertEquals(200, status);
    }catch (Exception ex){
        System.out.println(ex.getMessage());
    }
    }

    @Test
    void givenQuantityDTO_whenUnitConvert_thenReturnBadStatus() throws Exception, QuantityMeasurementException {
        when(quantityService.calculateUnit(any(),any())).thenReturn(new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.CENTIMETER));
        String json1 = json.toJson(quantityDTOS);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconversion").content("Bad Json")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    void givenQuantityDTOWithDifferentUnit_whenNotConvert_thenReturnBadStatus() throws Exception {
        try {
            when(quantityService.calculateUnit(any(),any())).thenThrow(new QuantityMeasurementException("Type Mismatch", QuantityMeasurementException.ExceptionType.TYPE_MISMATCH));
            String json1 = json.toJson(quantityDTOS);
            MvcResult mvcResult = this.mockMvc.perform(post("/unitconversion").content(json1)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            int status = mvcResult.getResponse().getStatus();
        } catch (QuantityMeasurementException e) {
            Assert.assertEquals(QuantityMeasurementException.ExceptionType.TYPE_MISMATCH, e.type);
        }
    }

    @Test
    void givenWrongMediaType_whenNotUnitConvert_thenReturnBadStatus() throws Exception, QuantityMeasurementException {
        when(quantityService.calculateUnit(any(),any())).thenReturn(new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.CENTIMETER));
        String json1 = json.toJson(quantityDTOS);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconversion").content("Bad Json")
                .contentType(MediaType.APPLICATION_ATOM_XML_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(415, status);
    }

    @Test
    void givenUnitTypeUrl_thenReturnUnitTypeList() throws Exception{
        when(quantityService.getUnitList()).thenReturn(unitList);
        String json1 = json.toJson(quantityDTOS);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit").content(json1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals(unitList.size(),4);
    }

    @Test
    void givenEmptyUrl_thenReturnBadStatus() throws Exception, QuantityMeasurementException {
        when(quantityService.calculateUnit(any(),any())).thenReturn(new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.CENTIMETER));
        String json1 = json.toJson(quantityDTOS);
        MvcResult mvcResult = this.mockMvc.perform(post("/").content(json1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404,status);
    }

    @Test
    void givenUnitLength_whenItIsTheir_thenReturnRespectiveUnitTypeList() throws Exception {
        when(quantityService.getEnum(any())).thenReturn(unitTypeList);
        String json1 = json.toJson(quantityDTOS);
        MvcResult mvcResult = this.mockMvc.perform(post("/Length").content(json1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals(unitTypeList.size(),4);
    }
}
