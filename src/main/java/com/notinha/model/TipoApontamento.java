package com.notinha.model;

public enum TipoApontamento {

	ALTERCAO("Alteração"),
	ARTE_SILK("Arte Silk"),
	ARTE_SUBLI("Arte Sublimação"),
	LAYOUT("Layout");
	
	
	
	private String descricao;
	
	TipoApontamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
