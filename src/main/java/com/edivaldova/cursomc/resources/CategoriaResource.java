package com.edivaldova.cursomc.resources;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edivaldova.cursomc.domain.Categoria;
import com.edivaldova.cursomc.services.CategoriaService;
import com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer;

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
}
