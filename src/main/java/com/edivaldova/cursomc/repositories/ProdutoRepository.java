package com.edivaldova.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edivaldova.cursomc.domain.Produto;

/* A interface ProdutoRepository será a responsável por permitir o acesso aos dados (busca, salvar, deletar, alterar) referentes 
 * ao objeto Produto.
*/
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
}
