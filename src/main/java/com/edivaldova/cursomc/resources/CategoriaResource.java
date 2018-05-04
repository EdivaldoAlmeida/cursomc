package com.edivaldova.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edivaldova.cursomc.domain.Categoria;
import com.edivaldova.cursomc.dto.CategoriaDTO;
import com.edivaldova.cursomc.services.CategoriaService;

@RestController //Essa classe (CategoriaResource) será um controlador Rest
/*
 * REST é um tipo de Web Service (abstrato) que faz o meio de campo entre aplicações independentes (feitas em linguagens 
 * diferentes e que rodam em SO diferentes). Segundo seu idealizador, para que uma APLICAÇÃO possa seguir a arquitetura REST
 * ele deverá atender às 6 constraints (conceitos ou regras) abaixo:
 * --> Arquitetura Cliente-servidor  
 * --> Stateless - aplicação não deve guardar estado no servidor. Dessa forma caso o cliente faça uma outra requsição outro servidor poderá atendê-lo
 * --> Cache - A aplicação deve ter a capacidade de fazer cache (reduzindo o tráfego de dados entre cliente-servidor)
 * --> Interface uniforme -
 * --> Sistema em camadas - Possibilidade de adicionar mais servidores em uma aplicação. Cliente não sabe qual servidor está lhe reespondendo
 * --> Código sob demanda - Aplicação poder evoluir para adaptar o cliente de acordo com novos requisitos e funcionalidades  
 * 	
 */
@RequestMapping(value="/categorias")
public class CategoriaResource {

	/*O método abaixo - listar() - é o responsável por responder pelo GET no recurso /categorias
	 * ele irá retornar uma lista de categorias
	*/
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id){			
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	//Inserindo uma nova categoria no BD, utilizando Json
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		URI uri	= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();		
		
		return ResponseEntity.created(uri).build();
	}
	
	//Atualizavalondo uma categoria no BD
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	//Endpoint para mostrar todas as categorias.
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll(){			
		List<Categoria> list = service.findAll();		
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
		/* para cada objeto desta lista tem q instanciar o dto correspondente
		 * STREAM é um recurso do JAVA 8. MAP faz uma operação para cada objeto da lista.
		 * -> é uma função anônima que recebe um obj e cria uma nova CategoriaDTO e passa esse objeto como argumento.
		 * Depois volta esse obj para o tipo lista utilizando o Collectors.toList. Resumindo transforma uma lista em outra... 			 */
	}
	
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> fidPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction){				
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);		
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
