package com.kiran.demo.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 
@RestControllerAdvice
public class GenerateExceptionHandling extends ResponseEntityExceptionHandler {
 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<String, Object>();
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }
 
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<String>("No such Id Exist in database.Please enter correct Id", HttpStatus.NOT_FOUND);
    }
 
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<String>("No such element Exist in database.Please enter correct Id",HttpStatus.NOT_FOUND);
    }
 
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("Please change the HTTP request method", HttpStatus.NOT_FOUND);
    }
 
    /*
     * @ExceptionHandler(MethodArgumentNotValidException.class) public
     * Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
     * Map<String,String> errorMessage=new HashMap<>();
     * ex.getBindingResult().getFieldError().forEach( error -> errorMessage.put(
     * error.getfield(),error.getDefaultMessage() ) );
     * 
     * return errorMessage; }
     */
 
}