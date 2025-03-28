package com.rubenrf.todo_list.infra.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.rubenrf.todo_list.dto.error.ErrorDTO;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDTO> appExceptionHandler(ApplicationException e) {
        String message = e.getMessage();
        HttpStatus status = e.getStatus();
        ErrorDTO error = new ErrorDTO(message);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDTO> noResourceFoundExceptionHandler(NoResourceFoundException e) {
        String message = e.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDTO error = new ErrorDTO(message);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDTO> noUsernameFoundExceptionHandler(UsernameNotFoundException e) {
        String message = e.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDTO error = new ErrorDTO(message);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ErrorDTO> httpClientErrorExceptionHandler(HttpClientErrorException.BadRequest e) {
        String message = e.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO error = new ErrorDTO(message);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<ErrorDTO> requestNotPermittedExceptionHandler(RequestNotPermitted e) {
        String message = e.getMessage();
        HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;
        ErrorDTO error = new ErrorDTO(message);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO error = new ErrorDTO(message);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        String message = e.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDTO error = new ErrorDTO(message);
        return new ResponseEntity<>(error, status);
    }

}
