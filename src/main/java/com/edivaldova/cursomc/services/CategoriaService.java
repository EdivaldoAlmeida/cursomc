package com.edivaldova.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivaldova.cursomc.domain.Categoria;
import com.edivaldova.cursomc.repositories.CategoriaRepository;
import com.edivaldova.cursomc.services.exceptions.ObjectNotFoundException;

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
	private CategoriaRepository repo; 
	
	//Atualização do método find(Integer id), face versão 2.0.0 do Spring não suportar o indicado na aula
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: "+
		Categoria.class.getName()));
		
		
	}
}
