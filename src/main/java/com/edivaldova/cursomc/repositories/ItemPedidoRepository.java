package com.edivaldova.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edivaldova.cursomc.domain.ItemPedido;

/* A interface CategoriaRepository será a responsável por permitir o acesso aos dados (busca, salvar, deletar, alterar) referentes 
 * ao objeto Categoria, que, por sua vez, está mapeado com
*/
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{
	
}
