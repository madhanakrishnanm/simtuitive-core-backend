package com.simtuitive.core.globalexception;
/**
 * 
 */


import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice

public class SimtuitiveControllerAdvice {

 

    private static final Logger logger = (Logger) LogManager.getLogger(SimtuitiveControllerAdvice.class);

 

    @ResponseStatus(HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value = {BadArgumentException.class})

    @ResponseBody

    ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {

        String url = req.getRequestURL().toString();

        logger.error("URL:: {} STATUS:: {} Exception:: {}", url, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());

        logger.debug(ex.getMessage(), ex);

        return new ErrorInfo(url, ex, logger.isDebugEnabled());

    }

 

   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(Exception.class)

    @ResponseBody

    ErrorInfo handleGeneric(HttpServletRequest req, Exception ex) {

        String url = req.getRequestURL().toString();

        logger.error("URL:: {} STATUS:: {} Exception:: {} Of {}", url, HttpStatus.INTERNAL_SERVER_ERROR,

                     ex.getClass().getCanonicalName(), ex.getLocalizedMessage());

        logger.debug(ex.getMessage(), ex);

        return new ErrorInfo(url, ex, HttpStatus.INTERNAL_SERVER_ERROR.value(), logger.isDebugEnabled());

    }

 

}