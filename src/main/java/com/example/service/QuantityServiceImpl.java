package com.example.service;

import com.example.exception.QuantityMeasurementException;
import com.example.quantityDTO.QuantityDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuantityServiceImpl implements IQuantityService {

    public QuantityDTO calculateUnit(QuantityDTO obj1 , QuantityDTO obj2) throws QuantityMeasurementException {
        QuantityComparison quantityComparison = new QuantityComparison();
        quantityComparison.getConvertedValue(obj1,obj2);
        return obj2;
    }

    public List getEnum(String unitType){
        List<QuantityConversion.MeasurementUnit> collect = Arrays.stream(QuantityConversion.MeasurementUnit.values())
                .filter(enumrator -> enumrator.unitType.equals(unitType)).collect(Collectors.toList());
        System.out.println(collect.size());
        return collect;
    }

    public List<String> getUnit1List(){
        List<String> unitList1 = Arrays.stream(QuantityConversion.MeasurementUnit.values())
                .map(list -> list.unitType).distinct().collect(Collectors.toList());
        System.out.println(unitList1);
        return unitList1;
    }
}