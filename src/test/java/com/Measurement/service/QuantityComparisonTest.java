package com.Measurement.service;

import com.Measurement.dto.QuantityDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuantityComparisonTest {

    //Length
    @Test
    public void givenFeetAndFeet_shouldReturnEqual() {
        com.Measurement.dto.QuantityDTO first = new com.Measurement.dto.QuantityDTO(0.0, QuantityConversion.MeasurementUnit.FEET);
        com.Measurement.dto.QuantityDTO second = new com.Measurement.dto.QuantityDTO(0.0, QuantityConversion.MeasurementUnit.FEET);
        Assert.assertEquals(first, second);
    }

    @Test
    public void givenSameReferenceOfFeet_shouldReturnEqual() {
        QuantityDTO first = new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.FEET);
        Assert.assertEquals(first, new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.FEET));
    }

    @Test
    public void givenSameClassOfFeet_shouldReturnEqual() {
        QuantityDTO first = new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.FEET);
        Assert.assertEquals(first.getClass(), (new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.FEET).getClass()));
    }

    @Test
    public void givenInchAndInch_shouldReturnEqual() {
        QuantityDTO first = new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.INCH);
        QuantityDTO second = new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.INCH);
        Assert.assertEquals(first, second);
    }

    @Test
    public void givenSameReferenceOfInch_shouldReturnTrue() {
        QuantityDTO first = new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.INCH);
        Assert.assertEquals(first, (new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.INCH)));
    }

    @Test
    public void givenSameClassOfInch_shouldReturnTrue() {
        QuantityDTO first = new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.INCH);
        Assert.assertEquals(first.getClass(), (new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.INCH).getClass()));
    }

    @Test
    public void givenFeetAndInch_shouldReturnEqual() {
        QuantityDTO first = new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.FEET);
        QuantityDTO second = new QuantityDTO(0.0, QuantityConversion.MeasurementUnit.INCH);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given1FeetAnd1Inch_shouldReturnFalse() {
        QuantityDTO first = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.FEET);
        QuantityDTO second = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.INCH);
        Assert.assertNotEquals(first, second);
    }

    @Test
    public void given1InchAnd1Feet_shouldReturnFalse() {
        QuantityDTO first = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.INCH);
        QuantityDTO second = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.FEET);
        Assert.assertNotEquals(first, second);
    }

    @Test
    public void given1FeetAnd12Inch_shouldReturnTrue() {
        QuantityDTO first = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.FEET);
        QuantityDTO second = new QuantityDTO(12.0, QuantityConversion.MeasurementUnit.INCH);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given12InchAnd1Feet_shouldReturnTrue() {
        QuantityDTO first = new QuantityDTO(12.0, QuantityConversion.MeasurementUnit.INCH);
        QuantityDTO second = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.FEET);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given3FeetAnd1Yard_shouldReturnTrue() {
        QuantityDTO first = new QuantityDTO(3.0, QuantityConversion.MeasurementUnit.FEET);
        QuantityDTO second = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.YARD);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given1FeetAnd1Yard_shouldReturnFalse() {
        QuantityDTO first = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.FEET);
        QuantityDTO second = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.YARD);
        Assert.assertNotEquals(first, second);
    }

    @Test
    public void given1InchAnd1Yard_shouldReturnFalse() {
        QuantityDTO first = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.INCH);
        QuantityDTO second = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.YARD);
        Assert.assertNotEquals(first, second);
    }

    @Test
    public void given1YardAnd36Inch_shouldReturnTrue() {
        QuantityDTO first = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.YARD);
        QuantityDTO second = new QuantityDTO(36.0, QuantityConversion.MeasurementUnit.INCH);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given36InchAnd1Yard_shouldReturnTrue() {
        QuantityDTO first = new QuantityDTO(36.0, QuantityConversion.MeasurementUnit.INCH);
        QuantityDTO second = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.YARD);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given1YardAnd3Feet_shouldReturnTrue() {
        QuantityDTO first = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.YARD);
        QuantityDTO second = new QuantityDTO(3.0, QuantityConversion.MeasurementUnit.FEET);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given2InchAnd5CM_shouldReturnTrue() {
        QuantityDTO first = new QuantityDTO(2.0, QuantityConversion.MeasurementUnit.INCH);
        QuantityDTO second = new QuantityDTO(5.08, QuantityConversion.MeasurementUnit.CENTIMETER);
        Assert.assertEquals(first, second);
    }

    //Volume

    @Test
    public void given1LiterAnd1000MilliLiter_shouldReturnTrue() {
        QuantityDTO first = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.LITRE);
        QuantityDTO second = new QuantityDTO(1000.0, QuantityConversion.MeasurementUnit.MILLI_LITRE);
        Assert.assertEquals(first, second);
    }

    //Weight

    @Test
    public void given1KiloGramAnd1000Gram_shouldReturnEqual() {
        QuantityDTO first = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.KILO_GRAMS);
        QuantityDTO second = new QuantityDTO(1000.0, QuantityConversion.MeasurementUnit.GRAMS);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given1TonAnd1000KG_shouldReturnEqual() {
        QuantityDTO first = new QuantityDTO(1.0, QuantityConversion.MeasurementUnit.TONNE);
        QuantityDTO second = new QuantityDTO(1000.0, QuantityConversion.MeasurementUnit.KILO_GRAMS);
        Assert.assertEquals(first, second);
    }

    //Temperature

    @Test
    public void given212FahrenheitAnd100Celsius_shouldReturnEqual() {
        QuantityDTO first = new QuantityDTO(212.0, QuantityConversion.MeasurementUnit.FAHRENHEIT);
        QuantityDTO second = new QuantityDTO(100.0, QuantityConversion.MeasurementUnit.CELSIUS);
        Assert.assertEquals(first, second);
    }
}