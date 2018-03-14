package com.machowina.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException ex) {
        
        return request.getRequestURL().toString() + " - " + ex.getMessage();
    }
	
	@ExceptionHandler(TicketAlreadyRunning.class)
    @ResponseStatus(value=HttpStatus.TOO_MANY_REQUESTS)
    @ResponseBody
    public String handleTicketAlreadyRunning(HttpServletRequest request, TicketAlreadyRunning ex) {
        
        return request.getRequestURL().toString() + " - Ticket already running. Last ticket for this car was not stopped.";
    }

}
