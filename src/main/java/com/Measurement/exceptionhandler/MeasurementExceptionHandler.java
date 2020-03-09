package com.Measurement.exceptionhandler;

import com.Measurement.exception.QuantityMeasurementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeasurementExceptionHandler {
    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity measurementExceptionHandler(QuantityMeasurementException q){
        return new ResponseEntity(q.type, HttpStatus.BAD_REQUEST);
    }
}
