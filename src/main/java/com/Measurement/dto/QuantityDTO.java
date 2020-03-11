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

    public QuantityDTO() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityDTO that = (QuantityDTO) o;
        QuantityConversion quantityConversion = new QuantityConversion();
        quantityConversion.getConverted(this,that);
        return Double.compare(that.value, value) == 0 &&
                (unit == that.unit || (that.value == 0 && value == 0));
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
