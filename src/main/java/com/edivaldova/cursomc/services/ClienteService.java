package com.edivaldova.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edivaldova.cursomc.domain.Cliente;
import com.edivaldova.cursomc.dto.ClienteDTO;
import com.edivaldova.cursomc.repositories.ClienteRepository;
import com.edivaldova.cursomc.services.exceptions.DataIntegrityException;
import com.edivaldova.cursomc.services.exceptions.ObjectNotFoundException;

/* Implementando o serviço que busca um cliente 
 * 
 */
@Service
public class ClienteService {
	/*
	 * Quando se declara uma depenência (ClienteRepository) dentro de uma classe e coloca o @Autowired
	 * essa dependência será automaticamente instanciada pelo Spring, pelo mecanismo de dependência ou inversão
	 * de controle. 
	 */
	@Autowired
	private ClienteRepository repo; 
	
	//Atualização do método find(Integer id), face versão 2.0.0 do Spring não suportar o indicado na aula
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: "+
		Cliente.class.getName()));
			
	}
	
	//Esse update permitirá atualizar os dados de um cliente com base nos dados já cadastrados desse cliente, por exemplo
	//Caso se queira alterar o nome do cliente este udate irá pegar os dados antigos (cpf, endereço, etc) e irá inserir nesse
	//cliente atualizado, senão ele iria retornar nula para esses dados.
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {	
		repo.deleteById(id);
		}catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir um cliente porque há entidades relacionadas");
		}
		
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	//Método que mostra as categorias em forma de paginação, de acordo com as carecterísticas informadas.
	//Page encapsula informações e operações sobre encapsulação.
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
	
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
		
	}
	//Atualiza os dados do cliente advindos do objeto obj
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
