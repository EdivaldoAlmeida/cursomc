package com.edivaldova.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivaldova.cursomc.domain.Categoria;
import com.edivaldova.cursomc.repositories.CategoriaRepository;

/* Implementando o serviço que busca uma categoria 
 * 
 */
@Service
public class CategoriaService {
	/*
	 * Quando se declara uma depenência (CategoriaRepository) dentro de uma classe e coloca o @Autowired
	 * essa dependência será automaticamente instanciada pelo Spring, pelo mecanismo de dependência ou inversão
	 * de controle. 
	 */
	@Autowired
	private CategoriaRepository repo; //objeto repo irá buscar no BD uma categoria, dado um certo id
	
	public Categoria buscar (Integer id) {
		Categoria obj = repo.getOne(id);
		
		return obj;
		
	}
}
