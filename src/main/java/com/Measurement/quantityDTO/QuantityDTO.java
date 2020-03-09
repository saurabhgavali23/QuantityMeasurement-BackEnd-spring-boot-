package com.Measurement.quantityDTO;

import com.Measurement.service.QuantityConversion;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
public class QuantityDTO {

    @NotNull
    public double value;
    @NotNull
    public QuantityConversion.MeasurementUnit unit;
}
