package com.Measurement.exception;

public class QuantityMeasurementException extends Throwable {

    public enum ExceptionType {
        NULL_EXCEPTION, TYPE_MISMATCH;
    }

    public ExceptionType type;

    public QuantityMeasurementException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
