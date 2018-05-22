package com.edivaldova.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

//Possui todos os dados de StandardError + a lista de mensagens dele
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
		
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);

	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	//Trocando o método setList por addError para que seja incluído um erro de cada vez e não a lista toda
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}



}
