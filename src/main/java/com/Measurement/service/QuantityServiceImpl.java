package com.Measurement.service;

import com.Measurement.exception.QuantityMeasurementException;
import com.Measurement.dto.QuantityDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuantityServiceImpl implements IQuantityService {

    @Override
    public QuantityDTO calculateUnit(QuantityDTO obj1 , QuantityDTO obj2) throws QuantityMeasurementException {
        QuantityComparison quantityComparison = new QuantityComparison();
        quantityComparison.getConvertedValue(obj1,obj2);
        return obj2;
    }

    @Override
    public List getEnum(String unitType){
        List<QuantityConversion.MeasurementUnit> collect = Arrays.stream(QuantityConversion.MeasurementUnit.values())
                .filter(enumrator -> enumrator.unitType.equals(unitType)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<String> getUnitList(){
        List<String> unitList1 = Arrays.stream(QuantityConversion.MeasurementUnit.values())
                .map(list -> list.unitType).distinct().collect(Collectors.toList());
        return unitList1;
    }
}