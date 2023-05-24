package com.socialrich.socialrich.exceptions.advices;

import com.socialrich.socialrich.dto.ErrorDTO;
import com.socialrich.socialrich.exceptions.NoRedSocialException;
import com.socialrich.socialrich.exceptions.NoUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(NoUserException.class)
    public ResponseEntity<ErrorDTO> noUserExceptionHandler ( NoUserException ex){
        ErrorDTO errorDTO = ErrorDTO.builder().code(NoUserException.getCodeError()).message(NoUserException.getMensaje()).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoRedSocialException.class)
    public ResponseEntity<ErrorDTO> noRedSocialExceptionHandler ( NoRedSocialException ex){
        ErrorDTO errorDTO = ErrorDTO.builder().code(NoUserException.getCodeError()).message(NoUserException.getMensaje()).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
