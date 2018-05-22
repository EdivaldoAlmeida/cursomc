package com.edivaldova.cursomc.resources.exceptions;

import java.io.Serializable;

//Classe auxiliar apenas para carregar os dados dos campos durante uma exceção
public class FieldMessage implements Serializable{	
	private static final long serialVersionUID = 1l;

	private String fieldName;
	private String message;
	
	public FieldMessage() {
		
	}
	
	
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}


	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMassage() {
		return message;
	}

	public void setMassage(String message) {
		this.message = message;
	}
	
	
	
}
