package com.example.quantityDTO;

import com.example.service.QuantityConversion;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
public class QuantityDTO {

    @NotNull
    public double value;
    @NotNull
    public QuantityConversion.MeasurementUnit unit;
}
