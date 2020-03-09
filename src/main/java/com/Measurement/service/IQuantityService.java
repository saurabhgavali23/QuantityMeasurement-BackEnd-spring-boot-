package com.Measurement.service;

import com.Measurement.exception.QuantityMeasurementException;
import com.Measurement.dto.QuantityDTO;

import java.util.List;

public interface IQuantityService {

    QuantityDTO calculateUnit(QuantityDTO obj1 , QuantityDTO obj2) throws QuantityMeasurementException;

    List getEnum(String unitType);

    List<String> getUnitList();
}
