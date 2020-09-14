package com.bruno.cursomongo.resources.exceptions;

import ch.qos.logback.core.status.Status;
import com.bruno.cursomongo.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Objeto nao encontrado.", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

}
