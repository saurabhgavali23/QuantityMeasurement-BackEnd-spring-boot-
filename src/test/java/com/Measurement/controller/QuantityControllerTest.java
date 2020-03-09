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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class QuantityControllerTest {

    QuantityDTO[] quantityDTOS = new QuantityDTO[2];
    Gson json = new Gson();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuantityServiceImpl quantityService;

    @BeforeEach
    void setUp() {
        for(int i=0;i<2;i++)
            quantityDTOS[i] = new QuantityDTO();
    }

    @Test
    void givenQuantityDTO_whenUnitConvert_thenReturnOKStatus() throws Exception, QuantityMeasurementException {
        quantityDTOS[0].unit = QuantityConversion.MeasurementUnit.CENTIMETER;
        quantityDTOS[0].value = 1;
        quantityDTOS[1].unit = QuantityConversion.MeasurementUnit.CENTIMETER;
        quantityDTOS[1].value = 1;
        when(quantityService.calculateUnit(quantityDTOS[0], quantityDTOS[1])).thenReturn(new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.CENTIMETER));
        String json1 = json.toJson(quantityDTOS);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconversion").content(json1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    void givenQuantityDTO_whenUnitConvert_thenReturnBadStatus() throws Exception, QuantityMeasurementException {
        quantityDTOS[0].unit = QuantityConversion.MeasurementUnit.CENTIMETER;
        quantityDTOS[0].value = 1;
        quantityDTOS[1].unit = QuantityConversion.MeasurementUnit.CENTIMETER;
        quantityDTOS[1].value = 1;
        when(quantityService.calculateUnit(quantityDTOS[0], quantityDTOS[1])).thenReturn(new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.CENTIMETER));
        String json1 = json.toJson(quantityDTOS);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconversion").content("Bad Json")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    void givenQuantityDTOWithDifferentUnit_whenNotConvert_thenReturnBadStatus() throws Exception {
        try {
            quantityDTOS[0].unit = QuantityConversion.MeasurementUnit.CENTIMETER;
            quantityDTOS[0].value = 1;
            quantityDTOS[1].unit = QuantityConversion.MeasurementUnit.GALLON;
            quantityDTOS[1].value = 1;
            when(quantityService.calculateUnit(quantityDTOS[0], quantityDTOS[1])).thenThrow(new QuantityMeasurementException("Type Mismatch", QuantityMeasurementException.ExceptionType.TYPE_MISMATCH));
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
        quantityDTOS[0].unit = QuantityConversion.MeasurementUnit.CENTIMETER;
        quantityDTOS[0].value = 1;
        quantityDTOS[1].unit = QuantityConversion.MeasurementUnit.CENTIMETER;
        quantityDTOS[1].value = 1;
        when(quantityService.calculateUnit(quantityDTOS[0], quantityDTOS[1])).thenReturn(new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.CENTIMETER));
        String json1 = json.toJson(quantityDTOS);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconversion").content("Bad Json")
                .contentType(MediaType.APPLICATION_ATOM_XML_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(415, status);
    }
}
