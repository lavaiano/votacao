package com.votacao.jdbc;

import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;


public abstract class JdbcDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	protected LocalDateTime dtInclusao;
	protected LocalDateTime dtAlteracao;

	public JdbcDomain() {
		super();
		dtInclusao = now();
	}

	public LocalDateTime getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(final LocalDateTime dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public LocalDateTime getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(final LocalDateTime dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}


}
