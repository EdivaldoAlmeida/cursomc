package com.edivaldova.cursomc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edivaldova.cursomc.domain.Categoria;
import com.edivaldova.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	
	//CategoriaRepository será responsável por salvar Categorias no BD
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Criando as categorias para serem serem inseridas automaticamente ao banco
		Categoria cat1 = new Categoria(null, "Informática"); //null pq o bd irá gerar id automaticamente
		Categoria cat2 = new Categoria(null, "Escritório");
		
		//Arrays.asList cria uma lista automaticamente com a quantidade necessária de itens
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}
	
	
}
