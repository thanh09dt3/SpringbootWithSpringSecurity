package com.example.demo.validation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SearchValidation{
	  @ExceptionHandler(Exception.class)
	  @ResponseStatus(HttpStatus.UNAUTHORIZED)
	  public ResponseEntity<String> handleNoSuchElementFoundException(
			  Exception exception
	  ) {
		  if (exception instanceof BindException) {
			  List<FieldError> errors = ((BindException) exception).getBindingResult().getFieldErrors();
		  }
		  return new ResponseEntity<String>("Tài khoản không hợp lệ \nLiên hệ support, gửi user đăng nhập để biết thêm chi tiết", HttpStatus.UNAUTHORIZED);
	  }

	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  public ResponseEntity<String> notFoundExcpetion(
			  Exception exception
	  ) {
		  return new ResponseEntity<String>("tét", HttpStatus.NOT_FOUND);
	  }

}
