package com.socialrich.socialrich.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController extends AbstractErrorController {
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }
    @RequestMapping
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        // Lógica para redireccionar a la página de error personalizada o hacer otra acción
        return ResponseEntity.status(status).body("Página no encontrada");
    }

}
