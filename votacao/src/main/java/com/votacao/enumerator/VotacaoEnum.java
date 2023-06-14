package com.votacao.enumerator;

public enum VotacaoEnum {
	
	PRIMEIRO_LUGAR(1,10),
	SEGUNDO_LUGAR(2,8),
	TERCEIRO_LUGAR(3,6),
	QUARTO_LUGAR(4,4),
	QUINTO_LUGAR(5,2),
	DECEPCAO(6,-5);

	private int codigo;
	
	private int nota;

	private VotacaoEnum(final int codigo, final int nota) {
		this.codigo = codigo;
		this.nota = nota;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public int getNota() {
		return nota;
	}



}
