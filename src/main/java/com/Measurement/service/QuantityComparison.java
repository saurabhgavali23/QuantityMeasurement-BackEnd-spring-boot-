package com.Measurement.service;

import com.Measurement.exception.QuantityMeasurementException;
import com.Measurement.dto.QuantityDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class QuantityComparison {

    @Autowired
    QuantityConversion quantityConversion;

    public QuantityComparison() {
        quantityConversion = new QuantityConversion();
    }

    public void getConvertedValue(QuantityDTO a, QuantityDTO b) throws QuantityMeasurementException {
        if (b.unit.unitType.equals(a.unit.unitType)) {
            if (b.unit.equals(QuantityConversion.MeasurementUnit.FAHRENHEIT)
                    || b.unit.equals(QuantityConversion.MeasurementUnit.CELSIUS))
                quantityConversion.getConversion(a, b);
            else
                b.value = a.unit.getUnitsSame(b.unit.getConverted(b.value));
            b.unit = a.unit;
        }
        else
            throw new QuantityMeasurementException("TYPE_MISMATCH",QuantityMeasurementException.ExceptionType.TYPE_MISMATCH);
    }
}