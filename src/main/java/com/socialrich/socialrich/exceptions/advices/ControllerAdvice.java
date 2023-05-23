package com.socialrich.socialrich.exceptions.advices;

import com.socialrich.socialrich.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> runtimeExceptionHandler ( RuntimeException ex){
    ErrorDTO errorDTO = ErrorDTO.builder().code("P-500").message(ex.getMessage()).build();
    return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }



}
