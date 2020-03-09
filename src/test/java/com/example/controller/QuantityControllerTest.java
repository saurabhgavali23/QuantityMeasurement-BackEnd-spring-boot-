package com.example.controller;

import com.example.exception.QuantityMeasurementException;
import com.example.quantityDTO.QuantityDTO;
import com.example.service.QuantityConversion;
import com.example.service.QuantityServiceImpl;
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

    QuantityDTO[] quantityDTOS = new QuantityDTO[4];
    Gson json = new Gson();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuantityServiceImpl quantityService;

    @BeforeEach
    void setUp() {
        quantityDTOS[0] = new QuantityDTO();
        quantityDTOS[1] = new QuantityDTO();
        quantityDTOS[0].unit = QuantityConversion.MeasurementUnit.CENTIMETER;
        quantityDTOS[1].unit = QuantityConversion.MeasurementUnit.CENTIMETER;
        quantityDTOS[2] = new QuantityDTO();
        quantityDTOS[3] = new QuantityDTO();
        quantityDTOS[2].unit = QuantityConversion.MeasurementUnit.CENTIMETER;
        quantityDTOS[3].unit = QuantityConversion.MeasurementUnit.TONNE;
        quantityDTOS[0].value = 0;
        quantityDTOS[1].value = 1;
    }

    @Test
    void givenQuantityDTO_whenUnitConvert_thenReturnOKStatus() throws Exception, QuantityMeasurementException {
        when(quantityService.calculateUnit(quantityDTOS[0], quantityDTOS[1])).thenReturn(null);
        String json1 = json.toJson(quantityDTOS);
        System.out.println(json1);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconversion").content(json1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    void givenQuantityDTO_whenUnitConvert_thenReturnBadStatus() throws Exception, QuantityMeasurementException {
        when(quantityService.calculateUnit(quantityDTOS[0], quantityDTOS[1])).thenReturn(null);
        String json1 = json.toJson(quantityDTOS);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconversion").content("Bad Json")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    void givenQuantityDTOWithDifferentUnit_whenNotConvert_thenReturnBadStatus() throws Exception {
        try {
            when(quantityService.calculateUnit(quantityDTOS[2], quantityDTOS[3])).thenThrow(new QuantityMeasurementException("Type Mismatch", QuantityMeasurementException.ExceptionType.TYPE_MISMATCH));
            String json1 = json.toJson(quantityDTOS);
            MvcResult mvcResult = this.mockMvc.perform(post("/unitconversion").content(json1)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            int status = mvcResult.getResponse().getStatus();
        } catch (QuantityMeasurementException e) {
            Assert.assertEquals(QuantityMeasurementException.ExceptionType.TYPE_MISMATCH, e.type);
        }
    }
}
