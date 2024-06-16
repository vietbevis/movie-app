package com.vietbevis.movies.exceptions;

import com.vietbevis.movies.exceptions.commons.DataExistsException;
import com.vietbevis.movies.exceptions.commons.DataNotFoundException;
import com.vietbevis.movies.responses.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<?> handleException(final MethodArgumentNotValidException ex) {
        Map<String, Object> details = new HashMap<>();
        List<Map<String, Object>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map((e) -> {
                    Map<String, Object> error = new LinkedHashMap<>();
                    error.put("field", e.getField());
                    error.put("message", e.getDefaultMessage());
                    return error;
                }).toList();
        details.put("errors", errors);
        return ResponseObject.getBuilder()
                .setCode(HttpStatus.BAD_REQUEST)
                .setMessage("Validation errors")
                .setData(details)
                .build();
    }

    @ExceptionHandler(DataExistsException.class)
    @ResponseBody
    public ResponseEntity<?> handleDataExistsException(final DataExistsException ex) {
        return ResponseObject.getBuilder()
                .setCode(HttpStatus.BAD_REQUEST)
                .setMessage(ex.getMessage())
                .setData(null)
                .build();
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleDataNotFoundException(final DataNotFoundException ex) {
        return ResponseObject.getBuilder()
                .setCode(HttpStatus.BAD_REQUEST)
                .setMessage(ex.getMessage())
                .setData(null)
                .build();
    }
}
