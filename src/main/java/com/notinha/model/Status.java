package com.notinha.model;

public enum Status {
	ABERTA("Aberta"), PAGA("Paga"), CRIACAO("Criação");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
