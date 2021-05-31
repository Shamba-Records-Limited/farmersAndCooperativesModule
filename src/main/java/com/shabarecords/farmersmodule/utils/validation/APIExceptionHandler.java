package com.shabarecords.farmersmodule.utils.validation;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shabarecords.farmersmodule.utils.APIResponse;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper mapper;
    private Logger LOGGER = LoggerFactory.getLogger(APIExceptionHandler.class);


    public APIExceptionHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Object> handleConflict(RuntimeException ex) {
        //LOGGER.error("Err", ex);
        return ResponseEntity.status(BAD_REQUEST).body(APIResponse.ofError(ex.getLocalizedMessage()));
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        // LOGGER.error("Err", ex);

        //Get all errors
        List<Map<String, String>> response = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            Map<String, String> errors = new HashMap<>();
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            response.add(errors);
        });

        String message = "Invalid request";
        try {
            message = mapper.writeValueAsString(response);
        } catch (Exception e) {

        }

        return ResponseEntity.status(BAD_REQUEST).body(APIResponse.ofError(message));
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        LOGGER.error("Err", ex);
        final Throwable cause = ex.getCause();

        String message = "";
        if (cause == null) {
            message = "Bad Request : Malformed Json Request: Required request body is missing,ensure well formatted body is provided";
        } else if (cause instanceof JsonParseException) {
            message = "JsonParseException : " + cause.getMessage();
        } else if (cause instanceof JsonMappingException) {

            String pathReference = ((JsonMappingException) cause).getPathReference();
            message = "JsonMappingException : unable to map " + pathReference.substring(pathReference.indexOf("["), pathReference.indexOf("]") + 1) + ". Requires well formatted request";
        } else {
            message = "Bad Request : Malformed Json Request: Required request body is missing,ensure well formatted body is provided";
        }


        return ResponseEntity.status(BAD_REQUEST).body(APIResponse.ofError(message));
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex
    ) {
        // LOGGER.error("Err", ex);
        return ResponseEntity.status(BAD_REQUEST).body(APIResponse.ofError(
                String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                        ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName())));


    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // LOGGER.error("Err", ex);
        return ResponseEntity.status(BAD_REQUEST).body(APIResponse.ofError(ex.getLocalizedMessage()));
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleThrowable(final Throwable ex) {
        if (ex instanceof ConstraintViolationException) {

            return ResponseEntity.status(BAD_REQUEST).body(APIResponse.ofError(ex.getLocalizedMessage()));
        } else if (ex instanceof JwtException) {
            return ResponseEntity.status(BAD_REQUEST).body(APIResponse.ofError("Expired or invalid JWT token"));
        }
        LOGGER.error("Unexpected Error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofError(ex.getLocalizedMessage()));
    }
}
