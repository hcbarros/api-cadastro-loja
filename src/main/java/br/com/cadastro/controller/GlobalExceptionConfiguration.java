package br.com.cadastro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cadastro.controller.response.ResponseError;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionConfiguration extends ResponseEntityExceptionHandler {

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
		
		return ResponseEntity.badRequest().body(new ResponseError(errors));
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
	    String error = "O parâmetro " + ex.getParameterName() + " está ausente!";
	   
	    return ResponseEntity.badRequest()
	    					 .body(new ResponseError(ex.getLocalizedMessage(), error));
	}
	
	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> handleCadastroException (
			RuntimeException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    
	    errors.add(ex.getMessage());

		return ResponseEntity.badRequest().body(new ResponseError(errors));
	}
	
	@ExceptionHandler({ EntityNotFoundException.class })
	public ResponseEntity<Object> handleEntityException (
			EntityNotFoundException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    
	    errors.add("A loja informada não existe!");

		return ResponseEntity.badRequest().body(new ResponseError(errors));
	}
		
	
}
