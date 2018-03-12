package com.edivaldova.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(method=RequestMethod.GET)
	public String listar(){
		return "Rest está funcionando!";
	}
}
