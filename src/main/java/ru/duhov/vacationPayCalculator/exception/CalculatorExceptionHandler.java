package ru.duhov.vacationPayCalculator.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CalculatorExceptionHandler {
    @ExceptionHandler(value = CalculatorException.class)
    public ResponseEntity<ExceptionResponse> handleCalculatorException(CalculatorException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        response.setHttpStatus(BAD_REQUEST);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }
}
