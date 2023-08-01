package com.hd.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author hdereli
 * @since 8/1/2023
 */
@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(Exception e){

        e.printStackTrace();
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ErrorResponse(status , e.getMessage()), status);
    }

    @ExceptionHandler(RestServiceException.class)
    public ResponseEntity<ErrorResponse> handleExceptions(RestServiceException e) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        e.printStackTrace();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage(),
                        "",
                        null,
                        e.getErrorCode()
                ),
                status
        );
    }

    // fallback method
    @ExceptionHandler(Exception.class) // exception handled
    public ResponseEntity<ErrorResponse> handleExceptions(Exception e) {
        // ... potential custom logic

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        e.printStackTrace();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }

    /**
     * HttpRequestMethodNotSupportedException
     * API de istek türü(POST, GET, PUT, DELETE) yanlış gönderildiğinde bu exception fırlatılır.
     * Bu exception fırlatıldığında 405 Method Not Allowed döner.
     * @param e
     * @return 405 Method Not Allowed
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMetodNotAllowedExceptions(HttpRequestMethodNotSupportedException e) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED; // 405
        e.printStackTrace();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }

    /**
     * MethodArgumentNotValidException
     * API de parametre yanlış gönderildiğinde bu exception fırlatılır.
     * Bu exception fırlatıldığında 400 Bad Request döner.
     * @param e
     * @return 400 Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

    }

    /**
        Bu şekilde yaparsan daha okunması zor bir mesaj basar yukarıdaki ise çok daha güzel açıklamaları parametre parametre verir.
     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException e) {
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        e.printStackTrace();
//
//        return new ResponseEntity<>(
//                new ErrorResponse(
//                        status,
//                        e.getMessage()
//                ),
//                status
//        );
//    }

    /**
     * MissingServletRequestParameterException
     * API de parametre eksik gönderildiğinde bu exception fırlatılır.
     * Bu exception fırlatıldığında 400 Bad Request döner.
     *
     * @param e
     * @return 400 Bad Request
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        e.printStackTrace();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(NoSuchElementException.class) // exception handled
    public ResponseEntity<ErrorResponse> handleExceptions(NoSuchElementException e) {
        // ... potential custom logic

        HttpStatus status = HttpStatus.NOT_FOUND; // 404
        e.printStackTrace();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }


}
