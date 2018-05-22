package com.edivaldova.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.edivaldova.cursomc.services.exceptions.DataIntegrityException;
import com.edivaldova.cursomc.services.exceptions.ObjectNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandler {
	//Classe manipuladora de exceções do recurso.

	//Essa anotation informa que esse é um tratador de exceção do tipo que está entre parêntreses. Nesse caso, de não encontrado
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> object(ObjectNotFoundException e,HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	
	}
	
	//Essa anotation informa que esse é um tratador de exceção do tipo de violação de 
	//integridade não permitindo a exclusão de catetoria que possui produto. 
	@ExceptionHandler(DataIntegrityException.class)
		public ResponseEntity<StandardError> DataIntegrity(DataIntegrityException e,HttpServletRequest request){
			StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
}
	
	
	//Tratamento de erro do tipo argumenton não válido (nome preenchimento obrigatório, tamanho do campo menor que o mínimo, etc)  
	@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e,HttpServletRequest request){
			ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
			
			//capturando a lista de erros da exceção MethodArgumentNotValidException
			for(FieldError x : e.getBindingResult().getFieldErrors()) {
				err.addError(x.getField(), x.getDefaultMessage());
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
}
	
	
	
}
