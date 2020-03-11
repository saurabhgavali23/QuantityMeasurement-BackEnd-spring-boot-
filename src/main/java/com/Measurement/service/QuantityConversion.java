package com.Measurement.service;

import com.Measurement.dto.QuantityDTO;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class QuantityConversion {

    public void getConverted(QuantityDTO a, QuantityDTO b) {
        if (a.unit.unitType.equals(b.unit.unitType)){
            b.value = a.unit.getUnitsSame(b.unit.getConverted(b.value));
            b.unit = a.unit;
        }
    }

    public void getConversion(QuantityDTO a, QuantityDTO b) {
        if (b.unit.equals(MeasurementUnit.FAHRENHEIT))
            b.value = (a.unit.getFehtoCel(b.value));
        else
            b.value = (a.unit.getCeltoFeh(b.value));
    }

    public enum MeasurementUnit {
        FEET(12, 1, "Length"), INCH(1, 1, "Length"), YARD(36, 1, "Length"), CENTIMETER(1, 2.54, "Length"),
        LITRE(1, 1, "Volume"), MILLI_LITRE(1, 1000, "Volume"), GALLON(3.78, 1, "Volume"),
        KILO_GRAMS(1, 1, "Weight"), GRAMS(1, 1000, "Weight"), TONNE(1000, 1, "Weight"),
        FAHRENHEIT(9.0, 5.0, "Temperature"), CELSIUS(9.0, 5.0, "Temperature");

        private final double i;
        private final double j;
        public final String unitType;

        MeasurementUnit(double i, double j, String unitType) {
            this.i = i;
            this.j = j;
            this.unitType = unitType;
        }

        public double getConverted(double value) {
            return value * i / j;
        }

        public double getUnitsSame(double value) {
            return value * j / i;
        }

        public double getCeltoFeh(@NotNull double value) {
            return (i / j) * value + 32;
        }

        public double getFehtoCel(double value) {
            return (value -32) * j / i;
        }
    }
}