package com.edivaldova.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edivaldova.cursomc.domain.Categoria;
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
	public ResponseEntity<?> find(@PathVariable Integer id){
		
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
		
	}
}
