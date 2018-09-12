package com.notinha.model;

public enum StatusApontamento {


	PENDENTE("Pendente"),
	FINALIZADO("Finalizado");
	
	private String descricao;
	
	StatusApontamento(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
