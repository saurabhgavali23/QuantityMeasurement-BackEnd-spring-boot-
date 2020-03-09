package com.Measurement.dto;

import com.Measurement.service.QuantityConversion;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
public class QuantityDTO {

    @NotNull
    public double value;
    @NotNull
    public QuantityConversion.MeasurementUnit unit;

    public QuantityDTO(@NotNull double value, @NotNull QuantityConversion.MeasurementUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public QuantityDTO() {
    }
}
