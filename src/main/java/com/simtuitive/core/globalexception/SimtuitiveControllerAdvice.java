package com.simtuitive.core.globalexception;
/**
 * 
 */


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
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
   
   @ResponseStatus(HttpStatus.UNAUTHORIZED)
   @ExceptionHandler(value = { InvalidTokenException.class, UnauthorizedUserException.class,
                               UnauthorizedClientException.class, SessionTimeoutException.class
                               })
   @ResponseBody
   ErrorInfo handleUnAutherized(HttpServletRequest req, Exception ex) {
       String url = req.getRequestURL().toString();
       //logger.error("URL:: {} STATUS:: {} Exception:: {} Of {}", url, HttpStatus.UNAUTHORIZED,
                 //   ex.getClass().getCanonicalName(), ex.getLocalizedMessage());
       //logger.debug(ex.getMessage(), ex);
       System.out.println("welcome here controller advice");
       return new ErrorInfo(url, ex, true);
   }

 

}