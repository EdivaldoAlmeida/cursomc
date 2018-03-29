package com.edivaldova.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivaldova.cursomc.domain.Pedido;
import com.edivaldova.cursomc.repositories.PedidoRepository;
import com.edivaldova.cursomc.services.exceptions.ObjectNotFoundException;

/* Implementando o serviço que busca um Pedido 
 * 
 */
@Service
public class PedidoService {
	/*
	 * Quando se declara uma depenência (PedidoRepository) dentro de uma classe e coloca o @Autowired
	 * essa dependência será automaticamente instanciada pelo Spring, pelo mecanismo de dependência ou inversão
	 * de controle. 
	 */
	@Autowired
	private PedidoRepository repo; 
	
	//Atualização do método find(Integer id), face versão 2.0.0 do Spring não suportar o indicado na aula
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: "+
		Pedido.class.getName()));
		
		
	}
}
