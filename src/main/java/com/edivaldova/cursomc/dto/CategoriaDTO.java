package com.edivaldova.cursomc.dto;

import java.io.Serializable;

import com.edivaldova.cursomc.domain.Categoria;

/*
 * Será utilizada para definir quais os dados a serem trafegados quando forem feitas operações
 * básicas em Categorias. No caso em tela somente irá aparecer o id e o nome
 */
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L; 
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {	
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
