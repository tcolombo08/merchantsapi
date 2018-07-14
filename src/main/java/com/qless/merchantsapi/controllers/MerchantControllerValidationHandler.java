package com.qless.merchantsapi.controllers;

import com.qless.merchantsapi.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MerchantControllerValidationHandler {

    @Autowired
    private MessageSource msgSource;


    /**
     * Handles validation errors in entities.
     * @param ex thrown exception to handle
     * @return message to return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> processValidationError(ConstraintViolationException ex) {

        Map<String, String> data = new HashMap<String, String>();
        data.put("message", "Invalid request parameter(s).");
        return data;
    }


    /**
     * Handles error when now records found.
     * @param ex thrown exception to handle
     * @return message to return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> processValidationError(EntityNotFoundException ex) {

        Map<String, String> data = new HashMap<String, String>();
        data.put("message", "No records found.");
        return data;
    }


    /**
     * Handles internal server errors.
     * @param ex thrown exception to handle
     * @return message to return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, String> processValidationError(Exception ex) {

        Map<String, String> data = new HashMap<String, String>();
        data.put("message", "The request failed due to an internal server error.");
        return data;
    }

}
