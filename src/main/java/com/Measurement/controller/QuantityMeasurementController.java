package com.Measurement.controller;

import com.Measurement.exception.QuantityMeasurementException;
import com.Measurement.quantityDTO.QuantityDTO;
import com.Measurement.service.QuantityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class QuantityMeasurementController {

    @Autowired
    QuantityServiceImpl quantityService;

    @PostMapping("/unitconversion")
    public QuantityDTO unitConversion(@Valid @RequestBody QuantityDTO[] quantityDTO) throws QuantityMeasurementException {
        QuantityDTO quantityDTO1 = quantityService.calculateUnit(quantityDTO[0], quantityDTO[1]);
        return quantityDTO1;
    }

    @GetMapping("/{unit}")
    public List getUnit(@PathVariable String unit){
        return quantityService.getEnum(unit);
    }

    @GetMapping("/unit1list")
    public List getUnit1List(){
        return quantityService.getUnit1List();
    }

}
