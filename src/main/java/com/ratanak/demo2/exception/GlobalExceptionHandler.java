package com.ratanak.demo2.exception;

import com.ratanak.demo2.exception.model.DuplicateResourceException;
import com.ratanak.demo2.exception.model.ResourceNotFoundException;
import com.ratanak.demo2.exception.model.UnprocessableEntityException;
import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponseModel> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponseModel("fail",ex.getMessage()));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<BaseResponseModel> handleDuplicateResourceException(DuplicateResourceException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new BaseResponseModel("fail",ex.getMessage()));
    }
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<BaseResponseModel> handlerUnprocessableEntity(UnprocessableEntityException ex){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new BaseResponseModel("fail",ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseModel> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BaseResponseModel("fail","unexpected error occured: " + ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponseWithDataModel> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            // insert into errors map
            errors.put(fieldName,message);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseResponseWithDataModel("fail","validation failed",errors));
    }
}