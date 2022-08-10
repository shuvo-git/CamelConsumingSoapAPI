package com.kona.demosmsgw.exception;

import com.kona.demosmsgw.exception.error.ErrorModel;
import com.kona.demosmsgw.exception.error.ErrorResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Validation {
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {

        List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());
        return ErrorResponse.builder()
                .errorMessages(errorMessages)
                .message("Validation Error")
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(value= HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleUnprosseasableMsgException(HttpMessageNotReadableException msgNotReadable) {
        // note that we've added new properties (message, status) to our ErrorResponse model return ErrorResponse.builder()
        return ErrorResponse.builder()
                .message("UNPROCESSABLE INPUT DATA")
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .build();
    }
}
