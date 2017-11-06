package br.com.concrete.desafio.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.concrete.desafio.dto.ResponseDTO;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	 @Override
	 protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			 HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ResponseDTO("Erro no JSON, verifique os campos!"), HttpStatus.BAD_REQUEST);
		 
	 }

	 @Override
	 @ResponseBody
	 public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			 HttpStatus status, WebRequest request) {
		 return new ResponseEntity<>(new ResponseDTO("EndPoint não encontrado!"), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(Exception.class)
	 @ResponseBody
	 public ResponseEntity<?> handleDefaultException(Exception ex) {
		 return new ResponseEntity<>(new ResponseDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 
}
