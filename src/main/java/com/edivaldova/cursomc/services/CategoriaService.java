package com.edivaldova.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edivaldova.cursomc.domain.Categoria;
import com.edivaldova.cursomc.repositories.CategoriaRepository;
import com.edivaldova.cursomc.services.exceptions.DataIntegrityException;
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
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {	
		repo.deleteById(id);
		}catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
		
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	//Método que mostra as categorias em forma de paginação, de acordo com as carecterísticas informadas.
	//Page encapsula informações e operações sobre encapsulação.
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
}
