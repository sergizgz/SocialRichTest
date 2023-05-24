package com.socialrich.socialrich.exceptions.advices;

import com.socialrich.socialrich.dto.ErrorDTO;
import com.socialrich.socialrich.exceptions.NoUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(NoUserException.class)
    public ResponseEntity<ErrorDTO> noUserExceptionHandler ( NoUserException ex){
        ErrorDTO errorDTO = ErrorDTO.builder().code(NoUserException.getCodeError()).message(ex.getMessage()).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }


}
