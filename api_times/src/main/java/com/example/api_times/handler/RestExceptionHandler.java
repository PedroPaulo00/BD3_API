package com.example.api_times.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.api_times.common.ConversorDataHora;
import com.example.api_times.domain.exception.ResourceBadRequestException;
import com.example.api_times.domain.exception.ResourceNotFoundException;
import com.example.api_times.domain.model.ErrorMessage;

@ControllerAdvice
public class RestExceptionHandler {
  
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlerResourceNotFoundExecption(ResourceNotFoundException ex){
        String dataHora = ConversorDataHora.converterDataParaDataHora(new Date());
        ErrorMessage erro =  new ErrorMessage(dataHora, 
        HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorMessage> handlerBadRequestResourceNotFoundExecption(ResourceBadRequestException ex){
        String dataHora = ConversorDataHora.converterDataParaDataHora(new Date());
        ErrorMessage erro =  new ErrorMessage(dataHora, 
        HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handlerRequestExecption(Exception ex){
        String dataHora = ConversorDataHora.converterDataParaDataHora(new Date());
        ErrorMessage erro =  new ErrorMessage(dataHora, 
        HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
