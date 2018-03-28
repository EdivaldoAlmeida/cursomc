package com.edivaldova.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");


private int cod;
private String descricao;

private EstadoPagamento(int cod, String descricao) {
	this.cod = cod;
	this.descricao = descricao;
}

public int getCod() {
	return cod;
}

public String getDescricao() {
	return descricao;
}

/*Método que, a partir de um código recebido, retorna o tipo de pagamento associado a
*este código. Ele é static p/ poder ser possível sua execução mesmo sem instanciar
*objetos
*/
public static EstadoPagamento toEnum(Integer cod) {
	if (cod == null) {
		return null;
	}
	
	//For que percorre todos os objetos x nos valores possíveis EstadoPagamento
	for(EstadoPagamento x: EstadoPagamento.values()) {
		if(cod.equals(x.getCod())) {
			return x;
		}
	}
	//Tratamento de exceção
	throw new IllegalArgumentException("Id inválido: " + cod);
}
}