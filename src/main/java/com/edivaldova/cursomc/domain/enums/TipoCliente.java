package com.edivaldova.cursomc.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	/*Método que, a partir de um código recebido, retorna o tipo de pessoa associado a
	*este código. Ele é static p/ poder ser possível sua execução mesmo sem instanciar
	*objetos
	*/
	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		//For que percorre todos os objetos x nos valores possíveis TipoCliente
		for(TipoCliente x: TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		//Tratamento de exceção
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
