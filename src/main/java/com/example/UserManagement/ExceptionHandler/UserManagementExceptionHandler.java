package com.example.UserManagement.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.UserManagement.Controller.UserController;
import com.example.UserManagement.Exception.UserNameAlreadyExistsException;
import com.example.UserManagement.Exception.UserNotFoundException;
import com.example.UserManagement.Model.ErrorDetails;

@RestControllerAdvice
public class UserManagementExceptionHandler {

	private static final  Logger LOGGER=LoggerFactory.getLogger(UserManagementExceptionHandler.class);
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		  List<String> errors = new ArrayList<>();
		    if (exception.hasErrors()) {
		    	Consumer<ObjectError> addToErrorsList=(x)->{errors.add(x.getDefaultMessage());};
		    	exception.getAllErrors().stream().forEach(addToErrorsList);
		    }
		    ErrorDetails errorDetails=new ErrorDetails(new Date(), errors);
		    return errorDetails;
	}
	@ExceptionHandler({UserNotFoundException.class, UserNameAlreadyExistsException.class})
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ErrorDetails handleUserNameNotValidException(Exception exception) {
		  List<String> errors = new ArrayList<>();
		  errors.add(exception.getMessage());
		  ErrorDetails errorDetails=new ErrorDetails(new Date(), errors);
		  return errorDetails;
	}
	@ExceptionHandler({DataAccessException.class})
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorDetails handleDataAccessException(DataAccessException exception) {
		  List<String> errors = new ArrayList<>();
		  errors.add(exception.getMessage());
		  ErrorDetails errorDetails=new ErrorDetails(new Date(), errors);
		  return errorDetails;
	}
}
